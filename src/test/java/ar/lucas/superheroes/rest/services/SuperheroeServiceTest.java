package ar.lucas.superheroes.rest.services;

import ar.lucas.superheroes.rest.models.entity.Superheroe;
import ar.lucas.superheroes.rest.repository.SuperheroeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Lucas Mussi
 * date 12/4/2021
 */

@ExtendWith(MockitoExtension.class)
public class SuperheroeServiceTest {

    @Mock
    private SuperheroeRepository superheroeRepository;

    @InjectMocks
    private SuperheroeServiceImpl superHeroeService;

    @Test
    void getAllSuperheroes_shouldReturnSuperheroeList() {
        List<Superheroe> list = new ArrayList<>();
        list.add(new Superheroe(1L, "Superman"));
        list.add(new Superheroe(2L, "Batman"));
        when(superheroeRepository.findAll()).thenReturn(list);
        List<Superheroe> listaObtenida = superheroeRepository.findAll();

        assertThat(listaObtenida).hasAtLeastOneElementOfType(Superheroe.class);
    }

    @Test
    void getAllSuperheroes_shouldReturnEmptyList() {
        List<Superheroe> list = new ArrayList<>();
        when(superheroeRepository.findAll()).thenReturn(list);
        List<Superheroe> listaObtenida = superheroeRepository.findAll();
        assertThat(listaObtenida).isEmpty();
    }

    @Test
    void getSuperheroeById_shouldReturnSuperheroe() {
        Superheroe superHeroe = new Superheroe(1L, "Superman");
        when(superheroeRepository.findById(any(Long.class))).thenReturn(Optional.of(superHeroe));
        Superheroe superHeroeObtenido = superHeroeService.findById(1L);
        assertThat(superHeroeObtenido).isNotNull();
    }

    @Test
    void getSuperheroeById_shouldReturnResponseStatusExceptionNotFound() {
        when(superheroeRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class, () -> superHeroeService.findById(1L));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    void buscarSuperheroe_shouldReturnSuperheroe() {
        List<Superheroe> list = new ArrayList<>();
        list.add(new Superheroe(1L, "Superman"));
        when(superheroeRepository.findAllByNombreContainingIgnoreCase(any(String.class))).thenReturn(list);

        List<Superheroe> resultados = superheroeRepository.findAllByNombreContainingIgnoreCase("man");
        assertThat(resultados).hasAtLeastOneElementOfType(Superheroe.class);
    }

}
