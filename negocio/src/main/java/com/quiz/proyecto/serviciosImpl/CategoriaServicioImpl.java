package com.quiz.proyecto.serviciosImpl;

import com.quiz.proyecto.entidades.Categoria;
import com.quiz.proyecto.repositorios.CategoriaRepo;
import com.quiz.proyecto.servicios.CategoriaServicio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServicioImpl implements CategoriaServicio {

    private final CategoriaRepo categoriaRepo;

    public CategoriaServicioImpl(CategoriaRepo categoriaRepo) {
        this.categoriaRepo = categoriaRepo;
    }

    @Override
    public Categoria crearCategoria(Categoria categoria) throws Exception {
        Optional<Categoria> buscado = categoriaRepo.findByNivel(categoria.getNivel());
        if(buscado.isPresent()){
            throw new Exception("Ya hay una categoria con este nivel");
        }
        return  categoriaRepo.save(categoria);
    }

    @Override
    public Categoria obtenerCategoria(int idCategoria) throws Exception {
        Optional<Categoria> buscado = categoriaRepo.findById(idCategoria);
        if(buscado.isEmpty()){
            throw new Exception("La categoria no existe");
        }
        return buscado.get();
    }

    @Override
    public List<Categoria> obtenerCategorias() {
        return categoriaRepo.findAll();
    }

    @Override
    public List<Categoria> obtenerCategoriasOrdenadas() {
        return categoriaRepo.obtenerCategoriasOrdenadas();
    }

    @Override
    public void eliminarCategoria(int idCategoria) throws Exception {
        categoriaRepo.deleteById(idCategoria);
    }

    @Override
    public void editarCategoria(Categoria categoria) throws Exception {
        Categoria buscado = categoriaRepo.findById(categoria.getIdCategoria()).orElseThrow(() -> new Exception("La categoria no existe"));

        Optional<Categoria> buscadoNivel = categoriaRepo.findByNivel(categoria.getNivel());

        if(buscadoNivel.isPresent()) {
            if (buscadoNivel.get().getIdCategoria() != categoria.getIdCategoria()) {
                throw new Exception("Ya hay una categoria con este nivel");
            }
        }
        categoriaRepo.save(categoria);
    }
}
