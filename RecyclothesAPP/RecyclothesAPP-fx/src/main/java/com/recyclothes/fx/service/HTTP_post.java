package com.recyclothes.fx.service;

import org.apache.commons.io.IOUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class HTTP_post
{
    static URL u;
    public static void main(String args[]) {

    }
    public static byte[] getImage(Long idFotoProducto){
        byte[] bytes = null;
        String s=URLEncoder.encode("A Test string to send to a servlet");
        try
        {
            HTTP_post post = new HTTP_post();
            post.u = new URL("http://localhost:8080/RecyclothesEAR-web/imageServlet?id="+idFotoProducto);

            // Open the connection and prepare to POST
            URLConnection uc = u.openConnection();
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setAllowUserInteraction(false);

            DataOutputStream dstream = new
                    DataOutputStream(uc.getOutputStream());

            // The POST line
            dstream.writeBytes(s);
            dstream.close();

//====================================================================
            // Read Response
            InputStream in = uc.getInputStream();

            bytes = IOUtils.toByteArray(in);        }
        catch (IOException e)
        {
            e.printStackTrace();// should do real exception handling
        }
        return bytes;
    }
}



