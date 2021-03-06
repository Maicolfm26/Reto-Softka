package com.quiz.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Respuesta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int idRespuesta;

    @Column(nullable = false)
    @NotBlank
    private String descripcion;

    @Column(nullable = false)
    private boolean valida;

    @ManyToOne(optional = false)
    private Pregunta pregunta;

    @OneToMany(mappedBy = "respuesta", cascade = {CascadeType.REMOVE})
    private List<Ronda> listadoRondas;

    public Respuesta(String descripcion, boolean valida, Pregunta pregunta) {
        this.descripcion = descripcion;
        this.valida = valida;
        this.pregunta = pregunta;
    }
}
