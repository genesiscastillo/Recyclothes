package com.recyclothes.remote;

import com.recyclothes.ejbclient.*;

public class RemoteEJBClient {


    public static void main(String[] args) throws Exception {

        ProductosEJB productosEJB = (ProductosEJB) EJBLocator.locateEJB(ProductosEJB.class);
        //ProductoDTO productoDTO = productosEJB.obtenerProducto(22L);
        //System.out.println("HOLA = "+ productoDTO);
    }
}