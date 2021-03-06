package com.quiz.proyecto.entidades;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS )
@MappedSuperclass
public class Persona implements Serializable {

    @Id
    @Column(length = 50)
    @EqualsAndHashCode.Include
    private String userName;

    @Column(nullable = false, length = 100)
    @NotBlank
    private String clave;

}
