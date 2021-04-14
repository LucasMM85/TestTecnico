package ar.lucas.superheroes.rest.services;

import ar.lucas.superheroes.rest.models.entity.Superheroe;
import ar.lucas.superheroes.rest.repository.SuperheroeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * @author Lucas Mussi
 * date 9/4/2021
 */

@Service
@RequiredArgsConstructor
public class SuperheroeServiceImpl implements SuperheroeService {

    private final SuperheroeRepository repository;

    @Override
    public List<Superheroe> findAll() {
        return repository.findAll();
    }

    @Override
    public Superheroe findById(Long id) {
        Optional<Superheroe> opt = repository.findById(id);
        if (opt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El superhéroe solicitado no existe.");
        }
        return opt.get();
    }

    @Override
    public List<Superheroe> buscar(String criterio) {
        List<Superheroe> superheroes = repository.findAllByNombreContainingIgnoreCase(criterio.toUpperCase());
        if (superheroes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el super héroe buscado");
        }
        return superheroes;
    }

    @Override
    public void update(Superheroe superHeroe) {
        if (!repository.existsById(superHeroe.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El héroe provisto no se encuentra cargado");
        }
        repository.save(superHeroe);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El héroe provisto no se encuentra cargado");
        }
        repository.deleteById(id);
    }
}
