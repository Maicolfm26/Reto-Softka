package com.quiz.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int idCategoria;

    @Column(nullable = false, length = 50)
    @NotBlank
    private String nombre;

    @Column(nullable = false, unique = true)
    @Min(1)
    private int nivel;

    @Column(nullable = false)
    @DecimalMin("0.1")
    private double dineroGanar;

    @OneToMany(mappedBy = "categoria", cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    private List<Pregunta> listadoPreguntas;

    @OneToMany(mappedBy = "categoria")
    private List<Ronda> listadoRondas;

    public Categoria(String nombre, int nivel, double dineroGanar) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.dineroGanar = dineroGanar;
    }
}
