package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.ArrayList;
import cl.ucn.disc.as.model.Departamento;


import javax.persistence.Entity;

/**
 * Class Edificio
 *
 * @autor Arquitectura de sistemas
 */

@ToString(callSuper = true)
@AllArgsConstructor
@Builder
@Entity
public class Edificio extends BaseModel {

    /**
     * The Name
     */
    @Getter
    @NotNull
    private String nombre;

    /**
     * The direccion
     */
    @Getter
    @NotNull
    private String direccion;

    /**
     * The Departamentos
     */
    @Getter
    private List<Departamento> departamentos;

    /**
     * Agrerga un departamento a la lista de departamentos
     */
    public void addDepartamento(Departamento departamento) {
        //initialize the lista si es nula
        if (this.departamentos == null) {
            this.departamentos = new ArrayList<>();
        }
        this.departamentos.add(departamento);
    }
}