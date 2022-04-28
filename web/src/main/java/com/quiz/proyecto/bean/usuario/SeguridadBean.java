package com.quiz.proyecto.bean.usuario;

import com.quiz.proyecto.entidades.Usuario;
import com.quiz.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {

    private final UsuarioServicio usuarioServicio;

    public SeguridadBean(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @Getter
    @Setter
    private boolean autenticado;

    @Getter @Setter
    private String userName;

    @Getter @Setter
    private String clave;

    @Getter @Setter
    private Usuario usuarioSesion;

    public String iniciarSesion() {
        try {
            usuarioSesion = usuarioServicio.iniciarSesion(userName, clave);
            autenticado = true;
            return "/usuario/index?faces-redirect=true";
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
        }
        return null;
    }


    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

    public void actualizarDinero(double dineroGanado) {
        usuarioSesion.aumentarDinero(dineroGanado);
        try {
            usuarioSesion = usuarioServicio.actualizarDinero(usuarioSesion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
