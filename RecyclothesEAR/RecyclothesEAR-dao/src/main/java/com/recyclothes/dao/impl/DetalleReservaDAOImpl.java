package com.recyclothes.dao.impl;

import com.recyclothes.dao.DetalleReservaDAO;
import com.recyclothes.dao.entity.DetalleReserva;
import com.recyclothes.dao.entity.Reserva;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DetalleReservaDAOImpl extends AbstractFacade<DetalleReserva> implements DetalleReservaDAO {

    static final Logger LOGGER = Logger.getLogger(DetalleReservaDAOImpl.class);

    public DetalleReservaDAOImpl()  {
        super(DetalleReserva.class);
    }

    @Override
    public DetalleReserva agregarDetalleReserva(DetalleReserva detalleReserva) {
        return create(detalleReserva);
    }

    @Override
    public List<DetalleReserva> obtenerProductosReserva(Long idReserva) {
        LOGGER.info("obtenerProductosReserva idReserva = " + idReserva);
        List<DetalleReserva> detalleReservaList = new ArrayList<>();
        try {
            Reserva reserva = new Reserva();
            reserva.setIdReserva(idReserva);
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<DetalleReserva> criteriaQuery = criteriaBuilder.createQuery(DetalleReserva.class);
            Root<DetalleReserva> root = criteriaQuery.from(DetalleReserva.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(criteriaBuilder.equal(root.get("reserva"), reserva ));
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
            detalleReservaList = getEntityManager().createQuery(criteriaQuery).getResultList();
        }catch(NoResultException e)     {
            LOGGER.error("ERROR obtenerProductosReserva", e);
        }
        return detalleReservaList;
    }
}
