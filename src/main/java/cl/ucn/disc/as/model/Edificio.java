package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * Class Edificio
 *
 * @autor Arquitectura de sistemas
 */

@Getter
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
     * The Departamentos of the Edificio
     */
    @OneToMany(cascade = CascadeType.ALL)
    @Getter
    private List<Departamento> departamentos;

    /**
     * Agrerga un departamento a la lista de departamentos
     */
    public void addDepartamento(Departamento departamento) {
        departamentos.add(departamento);
    }
}