package com.quiz.proyecto.bean.usuario;

import com.quiz.proyecto.entidades.Ronda;
import com.quiz.proyecto.servicios.RondaServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class RondaBean implements Serializable {

    private final RondaServicio rondaServicio;
    private final SeguridadBean seguridadBean;

    public RondaBean(RondaServicio rondaServicio, SeguridadBean seguridadBean) {
        this.rondaServicio = rondaServicio;
        this.seguridadBean = seguridadBean;
    }

    @Getter
    @Setter
    private List<Ronda> listadoRondas;

    @PostConstruct
    public void init() {
        listadoRondas = rondaServicio.obtenerRondasPorUsuario(seguridadBean.getUsuarioSesion().getUserName());
    }

}
