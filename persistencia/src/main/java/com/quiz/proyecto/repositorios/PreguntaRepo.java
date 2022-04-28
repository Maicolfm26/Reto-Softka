package com.quiz.proyecto.repositorios;

import com.quiz.proyecto.entidades.Admin;
import com.quiz.proyecto.entidades.Categoria;
import com.quiz.proyecto.entidades.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreguntaRepo extends JpaRepository<Pregunta, Integer> {

    List<Pregunta> findByCategoria(Categoria categoria);

}
