package ar.lucas.superheroes.rest;

import ar.lucas.superheroes.rest.models.entity.Superheroe;
import ar.lucas.superheroes.rest.services.SuperheroeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Lucas Mussi
 * date 14/4/2021
 */

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"classpath:data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class SuperheroesIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SuperheroeService service;

    @Test
    void getAll_shouldReturnSuperheroeList() throws Exception {
        mvc.perform(get("/superheroes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getById_shouldReturnSuperheroe() throws Exception {
        Long id = 1L;

        mvc.perform(get("/superheroes/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    void getById_shouldReturnSuperheroeNotFound() throws Exception {
        Long id = 55L;

        mvc.perform(get("/superheroes/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void buscar_shouldReturnSuperheroe() throws Exception {
        String criterio = "man";

        mvc.perform(get("/superheroes/busqueda/{criterio}", criterio))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void buscar_shouldReturnNotFound() throws Exception {
        String criterio = "josesito";

        mvc.perform(get("/superheroes/busqueda/{criterio}", criterio))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)));
    }

    @Test
    void update_shouldReturnNoContentandVerifyValue() throws Exception {
        Superheroe superheroe = new Superheroe(1L, "Superman modificado");

        mvc.perform(put("/superheroes")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(superheroe)))
                .andDo(print())
                .andExpect(status().isNoContent());

        Superheroe modificado = service.findById(1L);
        assertThat(modificado.getNombre()).isEqualTo("Superman modificado");
    }

    @Test
    void update_shouldReturnNotFound() throws Exception {
        Superheroe superheroe = new Superheroe(53L, "Seguro que no existe");

        mvc.perform(put("/superheroes")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(superheroe)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)));
    }

    @Test
    void delete_shouldReturnNoContentAndVerifyDeletion() throws Exception {
        Long id = 1L;

        mvc.perform(delete("/superheroes/{id}", id))
                .andExpect(status().isNoContent());

        assertThrows(ResponseStatusException.class, () -> service.findById(1L));
    }

    @Test
    void delete_shouldReturnNotFound() throws Exception {
        Long id = 555L;

        mvc.perform(delete("/superheroes/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)));
    }
}
