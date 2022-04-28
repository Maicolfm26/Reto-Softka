package com.quiz.proyecto.repositorios;

import com.quiz.proyecto.entidades.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaRepo extends JpaRepository<Respuesta, Integer> {
}
