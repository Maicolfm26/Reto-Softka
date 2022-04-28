package com.quiz.proyecto.servicios;

import com.quiz.proyecto.entidades.Ronda;
import com.quiz.proyecto.entidades.Usuario;

import java.util.List;

public interface RondaServicio {

    void guardarRonda(Ronda ronda) throws Exception;

    List<Ronda> obtenerRondasPorUsuario(String user_name);

}
