package com.quiz.proyecto.filtros;

import com.quiz.proyecto.bean.admin.SeguridadAdminBean;
import com.quiz.proyecto.bean.usuario.SeguridadBean;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SeguridadFilter implements Filter {

    public static final String PAGINA_INICIO = "/index.xhtml";
    public static final String PAGINA_INICIO_ADMIN = "/admin/login.xhtml";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        try {
            final HttpServletRequest request = (HttpServletRequest) servletRequest;
            final HttpServletResponse response = (HttpServletResponse) servletResponse;
            final String requestURI = request.getRequestURI();
            //Aplicar el filtro a esta carpeta
            if (requestURI.startsWith("/admin/")) {
                if (!requestURI.equals(PAGINA_INICIO_ADMIN)) {
                    //Obtenemos el objeto seguridadBean de la sesión actual
                    SeguridadAdminBean userManager = (SeguridadAdminBean)
                            request.getSession().getAttribute("seguridadAdminBean");
                    if (userManager != null) {
                        if (userManager.isAutenticado()) {
                            //El usuario está logueado entonces si puede ver la página solicitada
                            filterChain.doFilter(servletRequest, servletResponse);
                        } else {
                            //El usuario no está logueado, entonces se redirecciona al inicio
                            response.sendRedirect(request.getContextPath() + PAGINA_INICIO_ADMIN);
                        }
                    } else {
                        //El usuario no está logueado, entonces se redirecciona al inicio
                        response.sendRedirect(request.getContextPath() + PAGINA_INICIO_ADMIN);
                    }
                } else {
                    //Obtenemos el objeto seguridadBean de la sesión actual
                    SeguridadAdminBean userManager = (SeguridadAdminBean)
                            request.getSession().getAttribute("seguridadAdminBean");
                    if (userManager != null) {
                        if (userManager.isAutenticado()) {
                            response.sendRedirect(request.getContextPath() + "/admin/categorias.xhtml");
                        }
                    }
                    //La página solicitada es la de login por lo cual el filtro no aplica
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            } else if (requestURI.startsWith("/usuario/")) {
                //Obtenemos el objeto seguridadBean de la sesión actual
                SeguridadBean userManager = (SeguridadBean)
                        request.getSession().getAttribute("seguridadBean");
                if (userManager != null) {
                    if (userManager.isAutenticado()) {
                        //El usuario está logueado entonces si puede ver la página solicitada
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        //El usuario no está logueado, entonces se redirecciona al inicio
                        response.sendRedirect(request.getContextPath() + PAGINA_INICIO);
                    }
                } else {
                    //El usuario no está logueado, entonces se redirecciona al inicio
                    response.sendRedirect(request.getContextPath() + PAGINA_INICIO);
                }
            } else {
                //La página solicitada no está en la carpeta /usuario o /admin entonces el filtro no aplica
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
