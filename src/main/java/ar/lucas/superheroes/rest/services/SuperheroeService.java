package ar.lucas.superheroes.rest.services;

import ar.lucas.superheroes.rest.models.entity.Superheroe;

import java.util.List;

/**
 * @author Lucas Mussi
 * date 9/4/2021
 */

public interface SuperheroeService {

    List<Superheroe> findAll();

    Superheroe findById(Long id);

    List<Superheroe> buscar(String criterio);

    void update(Superheroe superHeroe);

    void delete(Long id);
}
