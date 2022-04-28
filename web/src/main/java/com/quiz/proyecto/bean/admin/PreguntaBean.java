package com.quiz.proyecto.bean.admin;

import com.quiz.proyecto.entidades.Categoria;
import com.quiz.proyecto.entidades.Pregunta;
import com.quiz.proyecto.entidades.Respuesta;
import com.quiz.proyecto.servicios.CategoriaServicio;
import com.quiz.proyecto.servicios.PreguntaServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Component
@ViewScoped
public class PreguntaBean implements Serializable {

    private final PreguntaServicio preguntaServicio;
    private final CategoriaServicio categoriaServicio;

    public PreguntaBean(PreguntaServicio preguntaServicio, CategoriaServicio categoriaServicio) {
        this.preguntaServicio = preguntaServicio;
        this.categoriaServicio = categoriaServicio;
    }

    @Value("#{param['idCategoria']}")
    @Getter
    private String codigoPreguntao;

    private Categoria categoria;

    @Getter
    @Setter
    private List<Pregunta> listadoPreguntas;

    @Getter
    @Setter
    private Pregunta selectedPregunta;

    @Getter
    @Setter
    private Respuesta respuesta1;
    @Getter
    @Setter
    private Respuesta respuesta2;
    @Getter
    @Setter
    private Respuesta respuesta3;
    @Getter
    @Setter
    private Respuesta respuesta4;

    @Getter
    @Setter
    private List<Pregunta> selectedPreguntas;

    @PostConstruct
    public void init() {
        if (codigoPreguntao != null && !codigoPreguntao.isEmpty()) {
            try {
                categoria = categoriaServicio.obtenerCategoria(Integer.parseInt(codigoPreguntao));
                listadoPreguntas = categoria.getListadoPreguntas();
            } catch (Exception e) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/admin/categorias.xhtml");
                } catch (IOException ex) {
                }
            }
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/admin/categorias.xhtml");
            } catch (IOException e) {
            }
        }
    }

    public void openNew() {
        this.selectedPregunta = new Pregunta();
        selectedPregunta.setCategoria(categoria);
        respuesta1 = new Respuesta();
        respuesta2 = new Respuesta();
        respuesta3 = new Respuesta();
        respuesta4 = new Respuesta();
        selectedPregunta.agregarRespuesta(respuesta1);
        selectedPregunta.agregarRespuesta(respuesta2);
        selectedPregunta.agregarRespuesta(respuesta3);
        selectedPregunta.agregarRespuesta(respuesta4);
    }

    public void savePregunta() {
        try {
            Integer idPregunta = selectedPregunta.getIdPregunta();
            selectedPregunta = preguntaServicio.agregarPregunta(selectedPregunta);
            if (idPregunta == null) {
                this.listadoPreguntas.add(this.selectedPregunta);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pregunta creada"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pregunta actualizada"));
            }
            PrimeFaces.current().executeScript("PF('managePreguntaDialog').hide()");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage()));
        }
        PrimeFaces.current().ajax().update("form:messages", "form:dt-preguntas");
    }

    public void deletePregunta() {
        try {
            preguntaServicio.eliminarPregunta(selectedPregunta.getIdPregunta());
            this.listadoPreguntas.remove(this.selectedPregunta);
            this.selectedPregunta = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pregunta eliminada"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-preguntas");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage()));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-preguntas");
        }
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedPreguntas()) {
            int size = this.selectedPreguntas.size();
            return size > 1 ? size + " preguntas seleccionadas" : "1 pregunta seleccionada";
        }

        return "Eliminar";
    }

    public boolean hasSelectedPreguntas() {
        return this.selectedPreguntas != null && !this.selectedPreguntas.isEmpty();
    }

    public void deleteSelectedPreguntas() {
        try {
            for (Pregunta pregunta : selectedPreguntas) {
                preguntaServicio.eliminarPregunta(pregunta.getIdPregunta());
            }
            this.listadoPreguntas.removeAll(this.selectedPreguntas);
            this.selectedPreguntas = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Preguntas eliminadas"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-preguntas");
            PrimeFaces.current().executeScript("PF('dtPreguntas').clearFilters()");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage()));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-preguntas");
            PrimeFaces.current().executeScript("PF('dtPreguntas').clearFilters()");
        }
    }

    public void seleccionarPregunta(Pregunta pregunta) {
        selectedPregunta = pregunta;
        respuesta1 = selectedPregunta.getListadoRespuestas().get(0);
        respuesta2 = selectedPregunta.getListadoRespuestas().get(1);
        respuesta3 = selectedPregunta.getListadoRespuestas().get(2);
        respuesta4 = selectedPregunta.getListadoRespuestas().get(3);
    }
}
