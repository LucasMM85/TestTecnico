package ar.lucas.superheroes.rest.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Lucas Mussi
 * date 13/4/2021
 */

@Entity
@Table(name = "superheroes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Superheroe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public String toJson() {
        return "{\"id\":" +
                id +
                "," +
                "\"nombre\":\"" +
                nombre +
                "\"}";
    }
}
