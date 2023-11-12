package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import java.util.List;
import java.util.ArrayList;

@ToString
@AllArgsConstructor
@Builder
@Entity
public class Contrato extends BaseModel {

    /**
     *The Contador
     */
    @NotNull
    @Getter
    private Date fechaPago;

    /**
     *The List of Pagos
     */
    @Getter
    private List<Pago> pagos;

    /**
     *The Persona
     */
    @Getter
    private Persona persona;

    /**
     *The Departamento
     */
    @Getter
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


    public long diferenciaDeDias(Date fecha) {
        Instant ahora = Instant.now();
        Duration diferencia = Duration.between(ahora, fecha.toInstant());
        return diferencia.toMinutes();
    }
}
