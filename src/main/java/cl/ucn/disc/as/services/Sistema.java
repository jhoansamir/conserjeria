package cl.ucn.disc.as.services;

import cl.ucn.disc.as.model.Contrato;
import cl.ucn.disc.as.model.Departamento;
import cl.ucn.disc.as.model.Edificio;
import cl.ucn.disc.as.model.Persona;

import java.util.Optional;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * System Operations
 *
 * @autor Jhoan Mamani Carrillo
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

    Departamento addDepartamento(Long idEdificio, Departamento departamento);

    /**
     * Realizar un contrato
     *
     * @param duenio a agregar
     * @param departamento a agregar
     * @param fechaPago a agregar
     */
    Contrato realizarContrato(Persona duenio, Departamento departamento, Instant fechaPago);

    Contrato realizarContrato(Long idPersona, Long idDepartamento, Instant fechaPago);

    /**
     * Obtener listado de personas del sistema.
     */
    List<Persona> getPersonas();

    /**
     * Obtener listado de contratos del sistema.
     */
    List<Contrato> getContratos();

    /**
     * Obtener persona dado su RUT.
     *
     * @param rut de la persona a consultar.
     * @return
     */
    Optional<Persona> getPersona(String rut);

    /**
     * Pobla la base de datos.
     */
    public void populate();
}
