package com.quiz.proyecto.entidades;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Admin extends Persona implements Serializable {

    public Admin(String user_name, @NotBlank String clave) {
        super(user_name, clave);
    }
}
