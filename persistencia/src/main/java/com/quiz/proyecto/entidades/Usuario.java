package com.quiz.proyecto.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Usuario extends Persona implements Serializable {

    @Column(nullable = false)
    private double dinero;

    @OneToMany(mappedBy = "usuario")
    private List<Ronda> listadoRondas;

    public Usuario(String userName, @NotBlank String clave) {
        super(userName, clave);
        this.dinero = 0;
    }

    public void aumentarDinero(double dinero) {
        this.dinero += dinero;
    }
}
