package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import java.util.List;
import java.util.ArrayList;


@AllArgsConstructor
@ToString(callSuper = true)
@Builder
@Entity
@Getter
public class Contrato extends BaseModel {

    /**
     *The Contador
     */
    @NotNull
    @Getter
    private Instant fechaPago;

    /**
     *The List of Pagos
     */
    @Getter
    @OneToMany(cascade = CascadeType.ALL)
    private List<Pago> pagos;

    /**
     *The Persona
     */
    @Getter
    @ManyToOne
    private Persona persona;

    /**
     *The Departamento
     */
    @Getter
    @ManyToOne
    private Departamento departamento;
    public static class ContratoBuilder {
        /**
         *
         * @return the Contrato
         */
        public Contrato build() {
            //inicializa si pagos es nulo
            if (this.pagos == null) {
                this.pagos = new ArrayList<>();
            }
            return new Contrato(fechaPago, pagos, persona, departamento);

        }
    }
}
