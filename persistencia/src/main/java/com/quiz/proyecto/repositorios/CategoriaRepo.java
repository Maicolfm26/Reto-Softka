package com.quiz.proyecto.repositorios;

import com.quiz.proyecto.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepo extends JpaRepository<Categoria, Integer> {

    Optional<Categoria> findByNivel(int nivel);

    @Query("select c from Categoria c where c.listadoPreguntas.size >= 5 order by c.nivel asc")
    List<Categoria> obtenerCategoriasOrdenadas();
}
