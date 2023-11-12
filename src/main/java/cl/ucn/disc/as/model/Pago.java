package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import lombok.Getter;

import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
@Getter
@ToString
@AllArgsConstructor
@Builder
@Entity
public class Pago {
    /**
     *  fecha de pago
     */
    private Date fechaPago;

    /**
     *  monto de pago
     */
    private Integer montoPago;
}
