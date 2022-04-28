package com.quiz.proyecto.bean.usuario;

import com.quiz.proyecto.entidades.*;
import com.quiz.proyecto.servicios.CategoriaServicio;
import com.quiz.proyecto.servicios.PreguntaServicio;
import com.quiz.proyecto.servicios.RondaServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class JuegoBean implements Serializable {

    private final RondaServicio rondaServicio;
    private final CategoriaServicio categoriaServicio;
    private final PreguntaServicio preguntaServicio;

    private final SeguridadBean seguridadBean;

    public JuegoBean(RondaServicio rondaServicio, CategoriaServicio categoriaServicio, PreguntaServicio preguntaServicio, SeguridadBean seguridadBean) {
        this.rondaServicio = rondaServicio;
        this.categoriaServicio = categoriaServicio;
        this.preguntaServicio = preguntaServicio;
        this.seguridadBean = seguridadBean;
    }

    @Getter
    @Setter
    private int numeroRonda;

    @Getter
    @Setter
    private List<Categoria> listadoCategorias;

    @Getter
    @Setter
    private Pregunta preguntaActual;

    @Getter
    @Setter
    private Categoria categoriaActual;

    @Getter
    @Setter
    private Respuesta respuesta;

    @Getter
    @Setter
    private String textoBoton = "Siguiente pregunta";

    @Getter
    @Setter
    private double dineroAcomulado;

    @Getter
    @Setter
    private String juegoIniciado = "Iniciar juego";

    @PostConstruct
    public String iniciarJuego() {
        juegoIniciado = "Terminar el juego iniciado";
        numeroRonda = 0;
        listadoCategorias = categoriaServicio.obtenerCategoriasOrdenadas();
        if (listadoCategorias.size() == 0) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/usuario/index.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            categoriaActual = listadoCategorias.get(numeroRonda);
            preguntaActual = preguntaServicio.obtenerPreguntaAleatoria(categoriaActual);
        }

        if (numeroRonda == listadoCategorias.size() - 1) {
            textoBoton = "Terminar juego";
        }
        return "juego?faces-redirect=true";
    }

    public String siguientePregunta() {
        if (respuesta != null) {
            Ronda ronda = new Ronda(seguridadBean.getUsuarioSesion(), categoriaActual, preguntaActual, respuesta);
            try {
                rondaServicio.guardarRonda(ronda);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("msj-juego", msg);
            }
            if (respuesta.isValida()) {
                dineroAcomulado += categoriaActual.getDineroGanar();
            } else {
                return "resultado?faces-redirect=true&amp;mensaje=" + "La respuesta es incorrecta, el dinero acomulado se ha perdido";
            }

            numeroRonda++;
            if (numeroRonda < listadoCategorias.size()) {
                categoriaActual = listadoCategorias.get(numeroRonda);
                preguntaActual = preguntaServicio.obtenerPreguntaAleatoria(categoriaActual);
                respuesta = null;
            } else {
                seguridadBean.actualizarDinero(dineroAcomulado);
                return "resultado?faces-redirect=true&amp;mensaje=" + "Has ganado el juego tu ganancia ha sido de $" + dineroAcomulado;
            }
            if (numeroRonda == listadoCategorias.size() - 1) {
                textoBoton = "Terminar juego";
            }
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Debes de seleccionar una respuesta");
            FacesContext.getCurrentInstance().addMessage("msj-juego", msg);
        }
        return null;
    }

    public String retirarme() {
        if (numeroRonda == 0) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Debes de responder al menos una pregunta");
            FacesContext.getCurrentInstance().addMessage("msj-juego", msg);
        } else {
            seguridadBean.actualizarDinero(dineroAcomulado);
            return "resultado?faces-redirect=true&amp;mensaje=" + "Te retiraste del juego con una ganancia de $" + dineroAcomulado;
        }
        return null;
    }
}
