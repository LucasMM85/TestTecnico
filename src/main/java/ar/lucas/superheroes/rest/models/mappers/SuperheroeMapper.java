package ar.lucas.superheroes.rest.models.mappers;

import ar.lucas.superheroes.rest.models.dto.SuperheroeDTO;
import ar.lucas.superheroes.rest.models.entity.Superheroe;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * @author Lucas Mussi
 * date 19/4/2021
 */

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface SuperheroeMapper {

    SuperheroeDTO toSuperheroe(Superheroe superheroe);
    List<SuperheroeDTO> toSuperheroe(List<Superheroe> superheroeList);
}
