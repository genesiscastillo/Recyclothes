package com.recyclothes.dao.impl;

import com.recyclothes.common.enums.CatalogoEnum;
import com.recyclothes.common.enums.EstadoProductoEnum;
import com.recyclothes.common.enums.TallaEnum;
import com.recyclothes.dao.ProductoDAO;
import com.recyclothes.dao.entity.Cliente;
import com.recyclothes.dao.entity.Producto;
import com.recyclothes.dao.entity.Reserva;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProductoDAOImpl extends AbstractFacade<Producto> implements ProductoDAO {

    static final Logger LOGGER = Logger.getLogger(ProductoDAOImpl.class);

    public ProductoDAOImpl(){
        super(Producto.class);
    }

    @Override
    public List<Producto> obtenerProductosAleatorio(int TOTAL_FOTOS_POR_PAGINA ) {
        LOGGER.info("obtenerProductosAleatorio");
        List<Producto> productoListDisponibles = obtenerProductosDisponibles(TOTAL_FOTOS_POR_PAGINA);
        List<Producto> productoListAleatorias = new ArrayList<>();
        if(!productoListDisponibles.isEmpty())  {
            if( productoListDisponibles.size() > 12) {
                int length = productoListDisponibles.size();
                Random random = new Random();
                do {
                    int index = random.nextInt(length);
                    Producto producto = productoListDisponibles.get(index);
                    if (!productoListAleatorias.contains(producto)) {
                        productoListAleatorias.add(producto);
                    }
                } while (productoListAleatorias.size() < 12);
            }else{
                productoListAleatorias.addAll(productoListDisponibles);
            }
        }
        return productoListAleatorias;
    }

    @Override
    public Reserva reservarProductos(Cliente cliente , List<Producto> productoList, String comentarios) {
        LOGGER.info("reservarProductos");
        Integer montoTotal = new Integer(0);
        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setComentarios(comentarios);
        for(Producto producto : productoList){
            producto.setEstadoProductoEnum(EstadoProductoEnum.RESERVADO);
            montoTotal = montoTotal + producto.getPrecioProducto().intValue();
            LOGGER.info("Resevando ->"+producto);
            update(producto);
        }
        reserva.setMontoTotal(montoTotal);
        return reserva;
    }

    @Override
    public Producto obtenerProducto(Long idProducto) {
        LOGGER.info("obtenerProducto idProducto=" + idProducto);
        return find(idProducto);
    }

    @Override
    public Producto registrarProducto(Producto producto) {
        LOGGER.info("registrarProducto producto=" + producto);
        return create(producto);
    }

    @Override
    public List<Producto> obtenerProductosDisponibles(int TOTAL_FOTOS_POR_PAGINA) {
        return  obtenerProductos(null, null, EstadoProductoEnum.DISPONIBLE , 1L , TOTAL_FOTOS_POR_PAGINA );
    }

    @Override
    public Producto actualizarProducto(Producto producto) {
        LOGGER.info("actualizarProducto "+producto);
        return update(producto);
    }

    @Override
    public List<Producto> obtenerProductos(CatalogoEnum catalogoEnum , TallaEnum tallaEnum , EstadoProductoEnum estadoProductoEnum, Long numeroPaginaActual ,int TOTAL_FOTOS_POR_PAGINA){
        LOGGER.info("obtenerProductos");
        List<Producto> productoList = new ArrayList<>();
        try {
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Producto> criteriaQuery = criteriaBuilder.createQuery(Producto.class);
            Root<Producto> root = criteriaQuery.from(Producto.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if(catalogoEnum != null) {
                predicates.add(criteriaBuilder.equal(root.get("catalogoEnum"), catalogoEnum));
            }
            if(tallaEnum != null) {
                predicates.add(criteriaBuilder.equal(root.get("tallaEnum"), tallaEnum));
            }
            if(estadoProductoEnum != null){
                predicates.add(criteriaBuilder.equal(root.get("estadoProductoEnum"), estadoProductoEnum ));
            }
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("fecIngreso")));
            productoList = getEntityManager()
                    .createQuery(criteriaQuery)
                    .setFirstResult((numeroPaginaActual.intValue() - 1) * TOTAL_FOTOS_POR_PAGINA)
                    .setMaxResults(TOTAL_FOTOS_POR_PAGINA)
                    .getResultList();
        }catch(NoResultException e)     {
            LOGGER.error("ERROR obtenerProductos", e);
        }
        return productoList;
    }


    @Override
    public Long obtenerNumeroPaginacion( CatalogoEnum catalogoEnum , TallaEnum tallaEnum , EstadoProductoEnum estadoProductoEnum , int TOTAL_FOTOS_POR_PAGINA) {
        LOGGER.info("obtenerNumeroPaginacion");
        String query = "SELECT COUNT(p.idProducto) FROM Producto p WHERE p.catalogoEnum= :catalogo and p.tallaEnum = :talla and p.estadoProductoEnum = :estado";
        TypedQuery<Long> typedQuery = getEntityManager().createQuery(query, Long.class);
        typedQuery.setParameter("catalogo"  , catalogoEnum );
        typedQuery.setParameter("talla"     , tallaEnum );
        typedQuery.setParameter("estado"    , estadoProductoEnum);
        Long countResult = typedQuery.getSingleResult();
        Long pageSize = Long.valueOf(TOTAL_FOTOS_POR_PAGINA);
        Long pageNumber;
        if(countResult % pageSize == 0) {
            pageNumber = ((countResult / pageSize));
        }else{
            pageNumber = ((countResult / pageSize) + 1);
        }
        return pageNumber;
    }

    @Override
    public Long obtenerTotalPrendas( CatalogoEnum catalogoEnum , TallaEnum tallaEnum , EstadoProductoEnum estadoProductoEnum) {
        String query = "SELECT COUNT(p.idProducto) FROM Producto p WHERE p.catalogoEnum= :catalogo and p.tallaEnum = :talla and p.estadoProductoEnum = :estado";
        TypedQuery<Long> typedQuery = getEntityManager().createQuery(query, Long.class);
        typedQuery.setParameter("catalogo", catalogoEnum);
        typedQuery.setParameter("talla", tallaEnum);
        typedQuery.setParameter("estado", estadoProductoEnum);
        Long countResult = typedQuery.getSingleResult();
        return countResult;
    }

    @Override
    public Boolean publicarProductosPendientes(CatalogoEnum catalogoEnum , TallaEnum tallaEnum ){
        LOGGER.info("publicarProductosPendientes ");
        CriteriaUpdate<Producto> criteriaUpdate = getEntityManager().getCriteriaBuilder().createCriteriaUpdate(Producto.class);
        Root<Producto> root = criteriaUpdate.from(Producto.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        if(catalogoEnum != null) {
            predicates.add(getEntityManager().getCriteriaBuilder().equal(root.get("catalogoEnum"), catalogoEnum));
        }
        if(tallaEnum != null) {
            predicates.add(getEntityManager().getCriteriaBuilder().equal(root.get("tallaEnum"), tallaEnum));
        }
        predicates.add(getEntityManager().getCriteriaBuilder().equal(root.get("estadoProductoEnum"), EstadoProductoEnum.PENDIENTE ));
        criteriaUpdate.set(root.get("estadoProductoEnum"), EstadoProductoEnum.DISPONIBLE);
        criteriaUpdate.where(getEntityManager().getCriteriaBuilder().and(predicates.toArray(new Predicate[predicates.size()])));
        getEntityManager().createQuery(criteriaUpdate).executeUpdate();
        return Boolean.TRUE;
    }

    @Override
    public Long totalProductoPorEstado(CatalogoEnum catalogoEnum, TallaEnum tallaEnum, EstadoProductoEnum estadoProductoEnum) {
        String query = "SELECT COUNT(p.idProducto) FROM Producto p WHERE p.catalogoEnum= :catalogo and p.tallaEnum = :talla and p.estadoProductoEnum = :estado";
        TypedQuery<Long> typedQuery = getEntityManager().createQuery(query, Long.class);
        typedQuery.setParameter("catalogo"  , catalogoEnum );
        typedQuery.setParameter("talla"     , tallaEnum );
        typedQuery.setParameter("estado"    , estadoProductoEnum);
        Long countResult = typedQuery.getSingleResult();
        return countResult;
    }
    //*--------------  new java 8
    @Override
    public List<Producto> obtenerProductos() {
        return getAll();
    }

    @Override
    public List<Producto> obtenerProductos(EstadoProductoEnum estadoProductoEnum ){
        LOGGER.info("obtenerProductos");
        List<Producto> productoList = new ArrayList<>();
        try {
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Producto> criteriaQuery = criteriaBuilder.createQuery(Producto.class);
            Root<Producto> root = criteriaQuery.from(Producto.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(criteriaBuilder.equal(root.get("estadoProductoEnum"), estadoProductoEnum ));
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("fecIngreso")));
            productoList = getEntityManager()
                    .createQuery(criteriaQuery)
                    .getResultList();
        }catch(NoResultException e)     {
            LOGGER.error("ERROR obtenerProductos", e);
        }
        return productoList;
    }

}
