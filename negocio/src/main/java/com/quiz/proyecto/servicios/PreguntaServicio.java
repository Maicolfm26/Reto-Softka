package com.quiz.proyecto.servicios;

import com.quiz.proyecto.entidades.Categoria;
import com.quiz.proyecto.entidades.Pregunta;

import java.util.List;

public interface PreguntaServicio {

    void eliminarPregunta(int idPregunta) throws Exception;

    Pregunta obtenerPregunta(int idPregunta) throws Exception;

    Pregunta agregarPregunta(Pregunta pregunta) throws Exception;

    Pregunta obtenerPreguntaAleatoria(Categoria categoria);

}
