package com.quiz.proyecto.repositorios;

import com.quiz.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, String> {
}
