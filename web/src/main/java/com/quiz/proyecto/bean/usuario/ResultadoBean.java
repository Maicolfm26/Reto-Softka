package com.quiz.proyecto.bean.usuario;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class ResultadoBean implements Serializable {

    @Value("#{param['mensaje']}")
    @Getter
    private String mensaje;

}
