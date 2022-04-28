package com.quiz.proyecto.repositorios;

import com.quiz.proyecto.entidades.Categoria;
import com.quiz.proyecto.entidades.Ronda;
import com.quiz.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RondaRepo extends JpaRepository<Ronda, Integer> {

    @Query("select r from Ronda r where r.usuario.userName = :user_name")
    List<Ronda> obtenerRondasPorUsuario(String user_name);
}
