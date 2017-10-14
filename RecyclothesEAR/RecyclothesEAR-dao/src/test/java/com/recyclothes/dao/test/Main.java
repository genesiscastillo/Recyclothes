package com.recyclothes.dao.test;

import com.recyclothes.common.enums.CatalogoEnum;
import com.recyclothes.common.enums.EstadoProductoEnum;
import com.recyclothes.common.enums.TallaEnum;
import com.recyclothes.dao.entity.Producto;
import com.recyclothes.dao.impl.ProductoDAOImpl;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Cesar on 19-04-2017.
 */
public class Main {
    public static void main(String[] args) {

        Long numeroPagiancion = 48L /24;
        System.out.println("numeroPaginacion="+numeroPagiancion);

    }
    public void test(){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        ProductoDAOImpl productoDAO = new ProductoDAOImpl();
        productoDAO.setEntityManager(em);
        //em.getTransaction().begin();
        List<Producto> productoList  =  productoDAO.obtenerProductos(EstadoProductoEnum.DISPONIBLE);
        productoList.stream().forEach( System.out::println );
        Producto producto = new Producto();
        producto.setIdProducto(8L);
        System.out.println("--->"+productoList.contains(producto));
        int index = productoList.indexOf(producto);
        System.out.println("--->"+productoList.get(index));
        //catalogo
        Long contador = productoList.stream()
                .filter((Producto producto2) -> CatalogoEnum.NINOS.equals(producto2.getCatalogoEnum()))
                .filter((Producto producto2) -> TallaEnum.RN_0_3.equals(producto2.getTalla()))
                .count();
        System.out.println("contador="+contador);
        //em.getTransaction().commit();
        em.close();
        PersistenceManager.INSTANCE.close();

    }
}
