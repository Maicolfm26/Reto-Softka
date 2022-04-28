package com.quiz.proyecto.servicios;

import com.quiz.proyecto.entidades.Categoria;

import java.util.List;

public interface CategoriaServicio {

    Categoria crearCategoria(Categoria categoria) throws Exception;

    Categoria obtenerCategoria(int idCategoria) throws Exception;

    List<Categoria> obtenerCategorias();

    List<Categoria> obtenerCategoriasOrdenadas();

    void eliminarCategoria(int idCategoria) throws Exception;

    void editarCategoria(Categoria categoria) throws Exception;

}
