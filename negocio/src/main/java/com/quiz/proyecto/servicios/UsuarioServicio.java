package com.quiz.proyecto.servicios;

import com.quiz.proyecto.entidades.Usuario;

public interface UsuarioServicio {

    Usuario registrarUsuario(Usuario usuario) throws Exception;

    Usuario iniciarSesion(String userName, String clave) throws Exception;

    Usuario actualizarDinero(Usuario usuario) throws Exception;

}
