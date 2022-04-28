package com.quiz.proyecto.serviciosImpl;

import com.quiz.proyecto.entidades.Categoria;
import com.quiz.proyecto.entidades.Pregunta;
import com.quiz.proyecto.repositorios.PreguntaRepo;
import com.quiz.proyecto.servicios.PreguntaServicio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreguntaServicioImpl implements PreguntaServicio {

    private final PreguntaRepo preguntaRepo;

    public PreguntaServicioImpl(PreguntaRepo preguntaRepo) {
        this.preguntaRepo = preguntaRepo;
    }

    @Override
    public void eliminarPregunta(int idPregunta) throws Exception {
        Pregunta pregunta = obtenerPregunta(idPregunta);
        preguntaRepo.deleteById(idPregunta);
    }

    @Override
    public Pregunta obtenerPregunta(int idPregunta) throws Exception {
        return preguntaRepo.findById(idPregunta).orElseThrow(() -> new Exception("La pregunta no existe"));
    }

    @Override
    public Pregunta agregarPregunta(Pregunta pregunta) throws Exception {
        if(!pregunta.isPreguntaCorrecta()) {
            throw new Exception("La pregunta debe de tener solo 4 y respuestas y una sola valida");
        }
        return preguntaRepo.save(pregunta);
    }

    @Override
    public Pregunta obtenerPreguntaAleatoria(Categoria categoria) {
        List<Pregunta> preguntas = preguntaRepo.findByCategoria(categoria);
        int numeroPreguntas = preguntas.size();
        int indiceAleatorio = (int) (Math.random()*numeroPreguntas);
        return preguntas.get(indiceAleatorio);
    }
}
