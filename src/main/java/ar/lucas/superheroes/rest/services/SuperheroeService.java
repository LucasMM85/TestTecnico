package ar.lucas.superheroes.rest.services;

import ar.lucas.superheroes.rest.models.dto.SuperheroeDTO;

import java.util.List;

/**
 * @author Lucas Mussi
 * date 9/4/2021
 */

public interface SuperheroeService {

    List<SuperheroeDTO> findAll();

    SuperheroeDTO findById(Long id);

    List<SuperheroeDTO> buscar(String criterio);

    void update(SuperheroeDTO superHeroe);

    void delete(Long id);
}
