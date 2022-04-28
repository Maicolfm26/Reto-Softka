package com.quiz.proyecto.serviciosImpl;

import com.quiz.proyecto.entidades.Ronda;
import com.quiz.proyecto.entidades.Usuario;
import com.quiz.proyecto.repositorios.RondaRepo;
import com.quiz.proyecto.servicios.RondaServicio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RondaServicioImpl implements RondaServicio {

    private final RondaRepo rondaRepo;

    public RondaServicioImpl(RondaRepo rondaRepo) {
        this.rondaRepo = rondaRepo;
    }

    @Override
    public void guardarRonda(Ronda ronda) throws Exception {
        rondaRepo.save(ronda);
    }

    @Override
    public List<Ronda> obtenerRondasPorUsuario(String user_name) {
        return rondaRepo.obtenerRondasPorUsuario(user_name);
    }
}
