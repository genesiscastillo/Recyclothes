package com.recyclothes.servlet;

import com.recyclothes.common.utils.Utils;
import com.recyclothes.singleton.AdministracionService;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by Cesar on 15-03-2016.
 */
@WebServlet("/confirmacion")
public class ConfirmacionCorreoServlet extends HttpServlet  {

    static final Logger LOGGER = Logger.getLogger(ConfirmacionCorreoServlet.class );
    private static final long serialVersionUID = 1L;

    @EJB
    AdministracionService administracionService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ipRemoteCliente = request.getRemoteAddr();
        LOGGER.info("entrando desde IP "+ipRemoteCliente+"-"+ Utils.getDiaFecha(Calendar.getInstance().getTime()));
        try {
            String token = String.valueOf(request.getParameter("token"));
            if (administracionService.confirmacionToken(token)) {
                response.sendRedirect("loginAdmin2.jsp");
            } else {
                response.sendRedirect("404.html");
            }
        }catch(Exception e) {
            response.sendRedirect("404.html");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
