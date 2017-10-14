package com.recyclothes.servlet;

import com.recyclothes.dao.FotoProductoDAO;
import com.recyclothes.dao.entity.FotoProducto;
import com.recyclothes.helper.ManagerService;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;

@WebServlet("/imageServlet")
public class ImageServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(ImageServlet.class );
    private static final long serialVersionUID = 1L;

    @EJB
    FotoProductoDAO fotoProductoDAO;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Not used in our simple example - see text.
        // String imageName = request.getParameter("imageName");

        Long idFotoProducto = Long.valueOf(request.getParameter("id"));
        String tiempo = request.getParameter("tiempo");

        LOGGER.info("\n\t\tidFotoProducto = " + idFotoProducto +" "+new Date().getTime());
        LOGGER.info("\n\t\ttiempo = " + tiempo);
        LOGGER.info("*******************************************");
        // For this example, just create our input stream from our sample byte array:
        FotoProducto fotoProducto = fotoProductoDAO.obtenerFotoProducto(idFotoProducto);

        //File file = new File("D:/proyectos/YamiletStore/YamiletStoreWeb/src/main/webapp/img/ropa"+idFotoProducto+".jpg");
        //ByteArrayInputStream iStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
        ByteArrayInputStream iStream = new ByteArrayInputStream(fotoProducto.getFoto());
        // Determine the length of the content data.
        // In our simple example, I can get the length from the hard-coded byte array.
        // If you're getting your imaqe from a database or file,
        // you'll need to adjust this code to do what is appropriate:
        //int length = file.length();
        int length = iStream.available();

        // Hard-coded for a GIF image - see text.
        response.setContentType("image/jpg");
        response.setContentLength(length);

        // Get the output stream from our response object, so we
        // can write our image data to the client:
        ServletOutputStream oStream = response.getOutputStream();

        // Now, loop through buffer reads of our content, and send it to the client:
        byte [] buffer = new byte[1024];
        int len = 0;
        while ((len = iStream.read(buffer)) != -1) {
            oStream.write(buffer, 0, len);
        }

        // Now that we've sent the image data to the client, close down all the resources:
        iStream.close();

        oStream.flush();
        oStream.close();

        // And we're done. Just let the method return at this point.
    }

    protected void doPost(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {

        // Pass through to the doPost method:
        doGet(request, response);
    }
} // end of ImageServlet