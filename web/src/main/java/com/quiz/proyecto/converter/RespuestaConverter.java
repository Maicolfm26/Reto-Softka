package com.quiz.proyecto.converter;

import com.quiz.proyecto.entidades.Respuesta;
import com.quiz.proyecto.repositorios.RespuestaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import java.io.Serializable;

@Component
public class RespuestaConverter implements Converter<Respuesta>, Serializable {

    @Autowired
    private RespuestaRepo respuestaRepo;

    @Override
    public Respuesta getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Respuesta respuesta = null;
        if (s != null && !"".equals(s)) {
            try {
                respuesta = respuestaRepo.findById(Integer.parseInt(s)).get(); //Hacer casting si es necesario
            } catch (Exception e) {
                throw new ConverterException(new FacesMessage(uiComponent.getClientId() + ":id no válido"));
            }
        }
        return respuesta;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Respuesta respuesta) {
        if (respuesta != null) {
            return respuesta.getIdRespuesta() + "";
        }
        return "";
    }
}
