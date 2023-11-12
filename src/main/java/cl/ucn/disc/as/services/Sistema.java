package cl.ucn.disc.as.services;

import cl.ucn.disc.as.model.Contrato;
import cl.ucn.disc.as.model.Departamento;
import cl.ucn.disc.as.model.Edificio;
import cl.ucn.disc.as.model.Persona;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import java.util.Date;

import javax.persistence.Entity;

/**
 * System Operations
 *
 * @autor Arquitectura de sistema
 */
public interface Sistema {
    /**
     * Agregar un edificio al sistema
     *
     * @param edificio a agregar
     */
    Edificio add(Edificio edificio);

    /**
     * Agregar una persona al sistema
     *
     * @param persona a agregar
     */
    Persona add(Persona persona);

    /**
     * Agregar un departamento al sistema
     *
     * @param edificio a agregar
     * @param departamento a agregar
     */
    Departamento addDepartamento(Edificio edificio,Departamento departamento);

    /**
     * Realizar un contrato
     *
     * @param duenio a agregar
     * @param departamento a agregar
     * @param fechaPago a agregar
     */
    Contrato realizarContrato(Persona duenio, Departamento departamento, Date fechaPago);
}
