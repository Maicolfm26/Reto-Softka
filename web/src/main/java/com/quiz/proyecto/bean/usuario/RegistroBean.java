package com.quiz.proyecto.bean.usuario;

import com.quiz.proyecto.entidades.Usuario;
import com.quiz.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class RegistroBean implements Serializable {

    @Getter
    @Setter
    private Usuario usuario;

    private final UsuarioServicio usuarioServicio;

    public RegistroBean(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
        usuario = new Usuario();
    }

    public void registrarUsuario() {
        try {
            usuarioServicio.registrarUsuario(usuario);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro exitoso");
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
        }
    }
}
