package com.recyclothes.dao.impl;

import com.recyclothes.dao.FotoProductoDAO;
import com.recyclothes.dao.entity.FotoProducto;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class FotoProductoDAOImpl extends AbstractFacade<FotoProducto> implements FotoProductoDAO {

    static final Logger LOGGER = Logger.getLogger(FotoProductoDAOImpl.class);

    public FotoProductoDAOImpl() {
        super(FotoProducto.class);
    }

    @Override
    public FotoProducto obtenerFotoProducto(Long idFotoProducto) {
        LOGGER.info("obtenerFotoProducto");
        return find(idFotoProducto);
    }

    @Override
    public FotoProducto registrarFotoProducto(FotoProducto fotoProducto) {
        LOGGER.info("registrarFotoProducto");
        return create(fotoProducto);
    }

    @Override
    public FotoProducto actualizarFotoProducto(FotoProducto fotoProducto) {
        LOGGER.info("actualizarFotoProducto");
        return update(fotoProducto);
    }

    @Override
    public List<FotoProducto> obtenerListaFotosProductosNuevo(Long numeroPaginaActual ,int TOTAL_FOTOS_POR_PAGINA ) {
        LOGGER.info("obtenerListaFotosProductosNuevo");
        List<FotoProducto> fotoProductos = new ArrayList<>();
        try{
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<FotoProducto> criteriaQuery = criteriaBuilder.createQuery(FotoProducto.class);
            Root<FotoProducto> root = criteriaQuery.from(FotoProducto.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(criteriaBuilder.equal(root.get("idProducto"), 0L));
            predicates.add(criteriaBuilder.isNotNull(root.get("foto")));
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
            //criteriaQuery.orderBy(criteriaBuilder.desc(root.get("idFotoProducto")));
            fotoProductos = getEntityManager()
                    .createQuery(criteriaQuery)
                    .setFirstResult((numeroPaginaActual.intValue() - 1) * TOTAL_FOTOS_POR_PAGINA)
                    .setMaxResults(TOTAL_FOTOS_POR_PAGINA)
                    .getResultList();
        }catch(NoResultException e){
            LOGGER.error("obtenerSiguienteIdFotoProducto", e);
        }
        return  fotoProductos;
    }

    @Override
    public Long obtenerNumeroPaginacion(int TOTAL_FOTOS_POR_PAGINA) {
        LOGGER.info("obtenerNumeroPaginacion");
        String query = "SELECT COUNT(f.idFotoProducto) FROM FotoProducto f WHERE f.idProducto = 0 and f.foto is not null";
        TypedQuery<Long> typedQuery = getEntityManager().createQuery(query, Long.class);
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
    public Long obtenerSiguienteIdFotoProducto() {
        LOGGER.info("obtenerSiguienteIdFotoProducto");
        Long idFotoProducto = Long.valueOf(0);
        try {
            String query = "SELECT f.idFotoProducto FROM FotoProducto f WHERE f.idProducto = 0";
            TypedQuery<Long> typedQuery = getEntityManager().createQuery(query, Long.class);
            typedQuery.getFirstResult();
            typedQuery.setMaxResults(1);
            idFotoProducto = typedQuery.getSingleResult();
            return idFotoProducto;
        } catch (NoResultException e) {
            LOGGER.error("obtenerSiguienteIdFotoProducto", e);
        }
        return idFotoProducto;
    }

    @Override
    public void eliminarFotoProducto(Long idFotoProducto) {
        remove(idFotoProducto);
    }

    @Override
    public List<FotoProducto> obtenerFotosAdjuntosDelProducto(Long idProducto) {
        LOGGER.info("obtenerFotoProductoDelProducto");
        List<FotoProducto> results = new ArrayList<>();

        try {
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<FotoProducto> criteriaQuery = criteriaBuilder.createQuery(FotoProducto.class);
            Root<FotoProducto> root = criteriaQuery.from(FotoProducto.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(criteriaBuilder.equal(root.get("idProducto"), idProducto));
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
            results = getEntityManager().createQuery(criteriaQuery).getResultList();
        }catch(NoResultException e)     {
            LOGGER.error("ERROR obtenerFotoProductoDelProducto", e);
        }
        return results;
    }
}
