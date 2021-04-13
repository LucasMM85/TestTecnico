package ar.lucas.superheroes.rest.repository;

import ar.lucas.superheroes.rest.models.entity.Superheroe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Lucas Mussi
 * date 9/4/2021
 */

public interface SuperheroeRepository extends JpaRepository<Superheroe, Long> {

    List<Superheroe> findAllByNombreContainingIgnoreCase(String criterio);
}
