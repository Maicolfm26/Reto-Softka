package com.quiz.proyecto.servicios;

import com.quiz.proyecto.entidades.Admin;

public interface AdminServicio {

    Admin iniciarSesion(String userName, String clave) throws Exception;
}
