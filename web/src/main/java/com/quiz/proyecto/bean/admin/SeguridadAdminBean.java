package com.quiz.proyecto.bean.admin;

import com.quiz.proyecto.entidades.Admin;
import com.quiz.proyecto.entidades.Usuario;
import com.quiz.proyecto.servicios.AdminServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Component
@Scope("session")
public class SeguridadAdminBean implements Serializable {

    private final AdminServicio adminServicio;

    public SeguridadAdminBean(AdminServicio adminServicio) {
        this.adminServicio = adminServicio;
    }

    @Getter
    @Setter
    private boolean autenticado;

    @Getter @Setter
    private String userName;

    @Getter @Setter
    private String clave;

    @Getter @Setter
    private Admin adminSesion;

    public String iniciarSesion() {
        try {
            adminSesion = adminServicio.iniciarSesion(userName, clave);
            autenticado = true;
            return "/admin/categorias.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
        }
        return null;
    }


    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/admin/login?faces-redirect=true";
    }
}
