package ar.lucas.superheroes.rest.controllers;

import ar.lucas.superheroes.rest.models.entity.Superheroe;
import ar.lucas.superheroes.rest.services.SuperheroeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Lucas Mussi
 * date 10/4/2021
 */

@WebMvcTest(controllers = SuperheroeController.class)
public class SuperheroeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SuperheroeService service;

    @Value("${apikey.name}")
    private String header;

    @Value("${apikey.value}")
    private String headerValue;

    @Test
    void shouldGetSuperheroesList() throws Exception {
        List<Superheroe> list = new ArrayList<>();
        list.add(new Superheroe(1L, "Superman"));
        list.add(new Superheroe(2L, "Batman"));
        when(service.findAll()).thenReturn(list);

        mvc.perform(get("/superheroes")
                .header(header, headerValue))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void shouldGetEmptySuperheroesList() throws Exception {
        List<Superheroe> list = new ArrayList<>();
        when(service.findAll()).thenReturn(list);

        mvc.perform(get("/superheroes")
                    .header(header, headerValue))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void getById_shouldReturnSuperheroe() throws Exception {
        when(service.findById(any(Long.class))).thenReturn(new Superheroe(1L, "Superman"));
        mvc.perform(get("/superheroes/1")
                .header(header, headerValue))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nombre", is("Superman")));
    }

    @Test
    void getById_shouldReturnNotFound() throws Exception {
        when(service.findById(any(Long.class))).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontrado"));
        mvc.perform(get("/superheroes/1")
                .header(header, headerValue))
           .andExpect(status().isNotFound());
    }

    @Test
    void buscar_shouldReturnSuperheroe() throws Exception {
        List<Superheroe> list = new ArrayList<>();
        list.add(new Superheroe(1L, "Superman"));
        list.add(new Superheroe(2L, "Batman"));
        when(service.buscar("man")).thenReturn(list);

        mvc.perform(get("/superheroes/busqueda/{criterio}", "man")
                .header(header, headerValue))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void buscar_shouldReturnNotFound() throws Exception {
        when(service.buscar(any(String.class))).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontrado"));
        mvc.perform(get("/superheroes/busqueda/{criterio}", "pepe")
                .header(header, headerValue))
                .andExpect(status().isNotFound());
    }

    @Test
    void update_shouldReturnNoContent() throws Exception {
        Superheroe superheroe = new Superheroe(1L, "Superman2");
        mvc.perform(put("/superheroes")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(superheroe))
                    .header(header, headerValue))
                .andExpect(status().isNoContent());
    }

    @Test
    void update_shouldReturnNotFound() throws Exception {
        Superheroe superheroe = new Superheroe(7L, "Superman7");
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontrado")).when(service).update(any(Superheroe.class));
        mvc.perform(put("/superheroes")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(superheroe))
                .header(header, headerValue))
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_shouldReturnNoContent() throws Exception {
        doNothing().when(service).delete(any(Long.class));
        mvc.perform(delete("/superheroes/{id}", 1)
                .header(header, headerValue))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete_shouldReturnNotFound() throws Exception {
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontrado")).when(service).delete(any(Long.class));
        mvc.perform(delete("/superheroes/{id}", 1)
                .header(header, headerValue))
                .andExpect(status().isNotFound());
    }

}
