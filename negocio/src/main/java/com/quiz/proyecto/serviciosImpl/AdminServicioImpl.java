package com.quiz.proyecto.serviciosImpl;

import com.quiz.proyecto.entidades.Admin;
import com.quiz.proyecto.repositorios.AdminRepo;
import com.quiz.proyecto.servicios.AdminServicio;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Service;

@Service
public class AdminServicioImpl implements AdminServicio {

    private final AdminRepo adminRepo;

    public AdminServicioImpl(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    @Override
    public Admin iniciarSesion(String userName, String clave) throws Exception {
        Admin admin = adminRepo.findById(userName).orElseThrow(() -> new Exception("Los datos de autenticacion son incorrectos"));

        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        if(passwordEncryptor.checkPassword(clave, admin.getClave())) {
            return admin;
        } else {
            throw new Exception("Los datos de autenticacion son incorrectos");
        }
    }
}
