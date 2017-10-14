package com.recyclothes.servlet;

import com.recyclothes.singleton.CatalogoService;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Cesar on 22-04-2016.
 */
@WebServlet
public class InitializedServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(InitializedServlet.class);

    @EJB
    CatalogoService catalogoService;

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                request.getRequestURI();
    }


}
