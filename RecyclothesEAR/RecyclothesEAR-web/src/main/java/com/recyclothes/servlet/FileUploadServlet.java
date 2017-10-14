package com.recyclothes.servlet;

import com.recyclothes.dao.FotoProductoDAO;
import com.recyclothes.dao.entity.FotoProducto;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@WebServlet(name = "FileUploadServlet", urlPatterns = {"/uploadFotos"})
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger(FileUploadServlet.class);

    @EJB
    FotoProductoDAO fotoProductoDAO;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request , response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        final Part filePart = request.getPart("file");
        final String fileName = getFileName(filePart);
        ByteArrayOutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter writer = response.getWriter();

        try {
            out = new ByteArrayOutputStream();
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            FotoProducto fotoProducto = new FotoProducto();
            fotoProducto.setFoto(out.toByteArray());
            fotoProducto = fotoProductoDAO.registrarFotoProducto(fotoProducto);

            writer.println("New file " + fileName + " created at " + fotoProducto);
            LOGGER.info("File{0}being uploaded to {1} = " + fileName);
            LOGGER.info("Saving " +fotoProducto);
        } catch (FileNotFoundException fne) {
            writer.println("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            writer.println("<br/> ERROR: " + fne.getMessage());

             LOGGER.error("Problems during file upload. Error: {0}", fne);
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.info("Part Header = {0}" + partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
