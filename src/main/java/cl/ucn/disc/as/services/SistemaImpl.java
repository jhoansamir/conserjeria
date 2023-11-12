package cl.ucn.disc.as.services;

import cl.ucn.disc.as.exceptions.SistemaException;

import io.ebean.Database;

import cl.ucn.disc.as.dao.PersonaFinder;

import cl.ucn.disc.as.model.Edificio;
import cl.ucn.disc.as.model.Persona;
import cl.ucn.disc.as.model.Contrato;
import cl.ucn.disc.as.model.Pago;
import cl.ucn.disc.as.model.Departamento;


import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.List;
import java.util.Locale;
import java.util.Optional;





import javax.persistence.PersistenceException;

/**
 * System Operations
 *
 * @autor Jhoan Mamani Carrillo
 */
@Slf4j
public class SistemaImpl implements Sistema {
    public SistemaImpl(Database database){
        this.database = database;
    }
    private final Database database;

    /**
     * Agrega un nuevo edificio a la base de datos.
     */
    @Override
    public Edificio add(@NotNull Edificio edificio){
        try {
            this.database.save(edificio);
        } catch (PersistenceException ex) {
            //TODO: save the exception
            log.error("Error", ex);
            throw new SistemaException("Error al agregar un edificio", ex);
        }
        return edificio;
    }
    /**
     * Agrega una nueva persona a la base de datos.
     */
    @Override
    public Persona add(@NotNull Persona persona){
        try {
            this.database.save(persona);
        } catch (PersistenceException ex) {
            log.error("Error", ex);
            throw new SistemaException("Error al agregar una persona", ex);
        }
        return persona;
    }

    /**
     * Agrega un nuevo departamento al edificio proporcionado.
     */
    @Override
    public Departamento addDepartamento(Edificio edificio, Departamento departamento) {
        try {
            edificio.addDepartamento(departamento);
            this.database.save(edificio);

        } catch (PersistenceException ex) {
            log.error("Error", ex);
            throw new SistemaException("Error al agregar un departamento", ex);
        }
        return departamento;
    }
    /**
     * Agrega un nuevo deparatamento al edificio proporcionando un id de edificio.
     */
    @Override
    public Departamento addDepartamento(Long idEdificio, Departamento departamento) {
        try {
            Edificio edificio = this.database.find(Edificio.class, idEdificio);

            if (edificio != null) {
                edificio.addDepartamento(departamento);
                this.database.save(edificio);
            } else {
                // Manejo cuando el edificio no se encuentra
                log.error("No se encontró el edificio con el ID: " + idEdificio);
            }
        } catch (PersistenceException ex) {
            // Manejo de excepciones de persistencia
            log.error("Error", ex);
            throw new SistemaException("Error al agregar un departamento", ex);
        }
        return departamento;
    }



    /**
     * Realiza un contrato entre una persona y un departamento.
     */
    @Override
    public Contrato realizarContrato(Persona duenio, Departamento departamento, Instant fechaPago) {

        try{
            Contrato contrato = Contrato.builder()
                    .departamento(departamento)
                    .persona(duenio)
                    .fechaPago(fechaPago)
                    .build();
            this.database.save(contrato);
            return contrato;
        }
        catch (PersistenceException ex){
            log.error("Error", ex);
            throw new SistemaException("Error al realizar contrato", ex);
        }
    }
    /**
     * Realiza un contranto entre una persona y un departamento.ingresando en id de la persona y el id del departamento.
     */
    @Override
    public Contrato realizarContrato(Long idPersona, Long idDepartamento, Instant fechaPago) {
        try {
            Persona persona = this.database.find(Persona.class, idPersona);
            Departamento departamento = this.database.find(Departamento.class, idDepartamento);

            if (persona != null && departamento != null) {
                Contrato contrato = Contrato.builder()
                        .departamento(departamento)
                        .persona(persona)
                        .fechaPago(fechaPago)
                        .build();
                this.database.save(contrato);
                return contrato;
            } else {
                // Manejo cuando la persona o el departamento no se encuentran
                log.error("No se encontró la persona con el ID: " + idPersona);
                log.error("No se encontró el departamento con el ID: " + idDepartamento);
            }
        } catch (PersistenceException ex) {
            // Manejo de excepciones de persistencia
            log.error("Error", ex);
            throw new SistemaException("Error al realizar contrato", ex);
        }
        return null;
    }

    @Override
    public List<Persona> getPersonas() {
        try{
            return this.database.find(Persona.class).findList();
        } catch (PersistenceException ex) {
            log.error("Error", ex);
            throw new SistemaException("Error al obtener las personas", ex);
        }
    }

    @Override
    public List<Contrato> getContratos() {
        return this.database.find(Contrato.class).findList();
    }

    public List<Pago> getPagos(String rut) {
        Contrato contrato = this.database.find(Contrato.class, rut);
        return contrato.getPagos();
    }

    @Override
    public Optional<Persona> getPersona(String rut) {
        try{
            return new PersonaFinder().byRut(rut);
        }
        catch (PersistenceException ex){
            log.error("Error", ex);
            throw new SistemaException("Error al obtener persona", ex);
        }
    }

    @Override
    public void populate() {
        Locale locale = new Locale("es-CL");
        FakeValuesService fvs = new FakeValuesService(locale, new RandomService());
        Faker faker = new Faker(locale);

        for (int i = 0; i < 1000; i++) {
            Persona persona = Persona.builder()
                    .rut(fvs.bothify("#########-#"))
                    .nombre(faker.name().firstName())
                    .apellidos(faker.name().lastName())
                    .email(faker.internet().emailAddress())
                    .telefono(faker.phoneNumber().cellPhone())
                    .build();
            this.add(persona);
        }
    }
}
