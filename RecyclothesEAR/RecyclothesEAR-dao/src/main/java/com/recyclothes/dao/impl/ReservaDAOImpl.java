package com.recyclothes.dao.impl;

import com.recyclothes.common.enums.EstadoReservaEnum;
import com.recyclothes.dao.ReservaDAO;
import com.recyclothes.dao.entity.Cliente;
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
public class ReservaDAOImpl extends AbstractFacade<Reserva> implements ReservaDAO {

    static final Logger LOGGER = Logger.getLogger(ReservaDAOImpl.class);

    public ReservaDAOImpl() {
        super( Reserva.class);
    }

    @Override
    public Reserva generarReserva(Reserva reserva) {
        return create(reserva);
    }

    @Override
    public List<Reserva> obtenerListaReservaCliente(Cliente cliente , String codigoReserva , EstadoReservaEnum... estadoReservaEnums ) {
        LOGGER.info("obtenerProductos");
        List<Reserva> reservaList = new ArrayList<>();
        try {
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Reserva> criteriaQuery = criteriaBuilder.createQuery(Reserva.class);
            Root<Reserva> root = criteriaQuery.from(Reserva.class);
            List<Predicate> predicates = new ArrayList<>();
            List<Predicate> predicates2 = new ArrayList<>();
            if(cliente != null) {
                predicates.add(criteriaBuilder.equal(root.get("cliente"), cliente));
            }
            if(estadoReservaEnums != null ) {
                for(EstadoReservaEnum estadoReservaEnum : estadoReservaEnums) {
                    predicates2.add(criteriaBuilder.equal(root.get("estadoReservaEnum"), estadoReservaEnum));
                }
                predicates.add(criteriaBuilder.or(predicates2.toArray(new Predicate[predicates2.size()])));
            }
            if(codigoReserva != null){
                predicates.add(criteriaBuilder.equal(root.get("codigoReserva"), codigoReserva ));
            }
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("fechaReserva")));
            reservaList = getEntityManager().createQuery(criteriaQuery).getResultList();
        }catch(NoResultException e)     {
            LOGGER.error("ERROR obtenerProductos", e);
        }
        return reservaList;
    }


    @Override
    public List<Reserva> obtenerListaReserva(Long idReserva , EstadoReservaEnum... estadoReservaEnums ) {
        LOGGER.info("obtenerProductos");
        List<Reserva> reservaList = new ArrayList<>();
        try {
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Reserva> criteriaQuery = criteriaBuilder.createQuery(Reserva.class);
            Root<Reserva> root = criteriaQuery.from(Reserva.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if(idReserva!= null) {
                predicates.add(criteriaBuilder.equal(root.get("idReserva"), idReserva));
            }

            if(estadoReservaEnums != null ) {
                for(EstadoReservaEnum reservaEnum : estadoReservaEnums ){
                    predicates.add(criteriaBuilder.equal(root.get("estadoReservaEnum"), reservaEnum));
                }
            }
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()])));
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get("fechaReserva")), criteriaBuilder.asc(root.get("estadoReservaEnum")), criteriaBuilder.asc(root.get("fechaEntrega")), criteriaBuilder.asc(root.get("horaEntregaEnum")));
            reservaList = getEntityManager().createQuery(criteriaQuery).getResultList();
        }catch(NoResultException e)     {
            LOGGER.error("ERROR obtenerProductos", e);
        }
        return reservaList;
    }

    @Override
    public Reserva obtenerReserva(Long idReserva) {
        LOGGER.info("obtenerReserva");
        Reserva reserva = null;
        if (idReserva != null) {
            reserva = find(idReserva);
        }
        return reserva;
    }

    @Override
    public Reserva obtenerReservaPorCodigoReserva(String codigoReserva) {
        Reserva reserva = null;
            try {
                CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
                CriteriaQuery<Reserva> criteriaQuery = criteriaBuilder.createQuery(Reserva.class);
                Root<Reserva> root = criteriaQuery.from(Reserva.class);
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (codigoReserva != null) {
                    predicates.add(criteriaBuilder.equal(root.get("codigoReserva"), codigoReserva));
                }
                criteriaQuery.select(root);
                criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
                reserva = getEntityManager().createQuery(criteriaQuery).getSingleResult();
            }catch(NoResultException e)     {
                LOGGER.error("ERROR obtenerReserva", e);
            }
        return reserva;
    }

    @Override
    public Reserva actualizarReserva(Reserva reserva) {
        LOGGER.info("actualizarReserva");
        reserva = update( reserva );
        return reserva;
    }
}
