package com.quiz.proyecto.repositorios;

import com.quiz.proyecto.entidades.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, String> {
}
