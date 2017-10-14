package com.recyclothes.dao.impl;

import com.recyclothes.dao.AccesoDAO;
import com.recyclothes.dao.entity.Acceso;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.NoResultException;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cesar on 17-08-2016.
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AccesoDAOImpl extends AbstractFacade<Acceso> implements AccesoDAO{

    static final Logger LOGGER = Logger.getLogger(AccesoDAOImpl.class);
    public AccesoDAOImpl(){
        super(Acceso.class);
    }

    @Override
    public Acceso generarAccesoUsuario(Acceso acceso) {
        return create(acceso);
    }

    @Override
    public void marcarAccesoUsuario(Acceso acceso) {
        update(acceso);
    }

    @Override
    public void eliminarAccesoServidor() {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaDelete<Acceso> criteriaDelete = criteriaBuilder.createCriteriaDelete(Acceso.class);
        Root<Acceso> root = criteriaDelete.from(Acceso.class);
        criteriaDelete.where(criteriaBuilder.equal( root.get("status") , 0));
        getEntityManager().createQuery(criteriaDelete).executeUpdate();
    }

    @Override
    public List<Acceso> obtenerListaAccesoActualizar() {
        List<Acceso> accesoList = new ArrayList<>();
        try{
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Acceso> criteriaQuery = criteriaBuilder.createQuery(Acceso.class);
            Root<Acceso> root = criteriaQuery.from(Acceso.class);
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(getEntityManager().getCriteriaBuilder().isNull(root.get("status")));
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
            accesoList = getEntityManager().createQuery(criteriaQuery).getResultList();
        }catch(NoResultException e) {
            LOGGER.error("obtenerListaAccesoActualizar" , e);
        }
        return accesoList;
    }
}
