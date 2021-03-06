package ar.lucas.superheroes.rest.controllers;

import ar.lucas.superheroes.annotations.Contar;
import ar.lucas.superheroes.rest.models.dto.SuperheroeDTO;
import ar.lucas.superheroes.rest.models.entity.Superheroe;
import ar.lucas.superheroes.rest.services.SuperheroeService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Lucas Mussi
 * date 9/4/2021
 */

@RestController
@RequestMapping("/superheroes")
@AllArgsConstructor
public class SuperheroeController {

    private final SuperheroeService service;

    @GetMapping
    @Contar
    public ResponseEntity<List<SuperheroeDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        SuperheroeDTO superHeroe = service.findById(id);
        return ResponseEntity.ok(superHeroe);
    }

    @GetMapping("/busqueda/{criterio}")
    public ResponseEntity<?> buscar(@PathVariable String criterio) {
        List<SuperheroeDTO> superHeroes = service.buscar(criterio);
        return ResponseEntity.ok(superHeroes);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping
    public ResponseEntity<?> update(@RequestBody SuperheroeDTO superHeroe) {
        service.update(superHeroe);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
