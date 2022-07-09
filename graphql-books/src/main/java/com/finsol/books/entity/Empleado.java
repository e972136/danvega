package com.finsol.books.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotEmpty
    String nombre;

    @NotEmpty
    @Email
    String email;

    @NotEmpty
    String telefono;

    @NotEmpty
    String genero;

    @Positive
    BigDecimal salario;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date fechaCreado;

    public String getSalarioStr(){
        DecimalFormat df = new DecimalFormat("###,###.00");
        String format = df.format(salario);
        return format;
    }
}
