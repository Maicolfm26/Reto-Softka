package com.quiz.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Ronda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int idRonda;

    @Column(nullable = false)
    private LocalDate fecha;

    @ManyToOne(optional = false)
    private Usuario usuario;

    @ManyToOne(optional = false)
    private Categoria categoria;

    @ManyToOne(optional = false)
    private Pregunta pregunta;

    @ManyToOne(optional = false)
    private Respuesta respuesta;

    public Ronda(Usuario usuario, Categoria categoria, Pregunta pregunta, Respuesta respuesta) {
        this.usuario = usuario;
        this.categoria = categoria;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.fecha = LocalDate.now();
    }
}
