package com.recyclothes.dao.impl;

import com.recyclothes.dao.ClienteDAO;
import com.recyclothes.dao.entity.Cliente;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.NoResultException;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ClienteDAOImpl extends AbstractFacade<Cliente> implements ClienteDAO {

    static final Logger LOGGER = Logger.getLogger(ClienteDAOImpl.class);

    public ClienteDAOImpl() {
        super(Cliente.class);
    }

    @Override
    public Cliente obtenerCliente(Long idCliente) {
        return find(idCliente);
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        return update(cliente);
    }

    @Override
    public Cliente recuperarClaveUsuario(String email){
        return validarAcceso(email);
    }
    @Override
    public Cliente validarAcceso(String correo) {
        try {
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
            Root<Cliente> root = criteriaQuery.from(Cliente.class);

            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(criteriaBuilder.equal(   root.get("correo")  , correo));
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));

            return getEntityManager().createQuery(criteriaQuery).getSingleResult();
        }catch(NoResultException e){
            LOGGER.error("No existe correo "+correo);
        }
        return null;
    }

    @Override
    public Cliente registrarCliente(Cliente cliente) {
        LOGGER.info("registrarCliente cliente  =" + cliente);
        cliente = create(cliente);
        return cliente;
    }

    @Override
    public Boolean validarConfirmacionToken(String token) {
        LOGGER.info("validarConfirmacionToken token  =" + token);
        CriteriaUpdate criteriaUpdate = getEntityManager().getCriteriaBuilder().createCriteriaUpdate(Cliente.class);
        Root<Cliente> root = criteriaUpdate.from(Cliente.class);
        criteriaUpdate.set(root.get("token") , "" );
        criteriaUpdate.where( getEntityManager().getCriteriaBuilder().equal(root.get("token") , token));
        getEntityManager().createQuery(criteriaUpdate).executeUpdate();
        return Boolean.TRUE;
    }

    @Override
    public List<Cliente> obtenerListaCliente(String nombres, String correo, String telefono) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
        Root<Cliente> root = criteriaQuery.from(Cliente.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        if(nombres != null){
            Expression<String> path = root.get("nombres");
            predicates.add(criteriaBuilder.like(   path  , '%'+nombres+'%'));
        }
        if(correo != null){
            Expression<String> path = root.get("correo");
            predicates.add(criteriaBuilder.like(   path  , '%'+correo+'%'));
        }
        if(telefono != null){
            Expression<String> path = root.get("telefono");
            predicates.add(criteriaBuilder.like(   path  , '%'+telefono+'%'));
        }
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));

        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }
}