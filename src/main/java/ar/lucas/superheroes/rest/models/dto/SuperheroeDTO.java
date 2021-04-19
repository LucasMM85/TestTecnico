package ar.lucas.superheroes.rest.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Lucas Mussi
 * date 19/4/2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperheroeDTO {

    private Long id;
    private String nombre;
}
