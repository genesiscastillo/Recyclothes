package com.recyclothes.service;

import com.recyclothes.dao.AccesoDAO;
import com.recyclothes.ejbclient.ActualizacionAccesoEJB;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Created by Cesar on 17-08-2016.
 */
@Stateless
@Remote(ActualizacionAccesoEJB.class)
public class ActualizacionAccesoEJBBean implements  ActualizacionAccesoEJB {

    final Long STATUS_SERVIDOR_OPENSHIFT = 0l;

    @EJB
    AccesoDAO accesoDAO;

/*
    @Override
    public List<AccesoDTO> obtenerListaAccesoActualizar() {
        List<AccesoDTO> accesoDTOs = new ArrayList<>();
        List<Acceso> accesoList = accesoDAO.obtenerListaAccesoActualizar();
        for(Acceso acceso : accesoList){
            AccesoDTO accesoDTO = Translation.convertToDTO(acceso);
            acceso.setStatus(STATUS_SERVIDOR_OPENSHIFT);
            accesoDAO.marcarAccesoUsuario(acceso);
            accesoDTOs.add(accesoDTO);
        }
        return accesoDTOs;
    }

    @Override
    public void eliminarAccesoMarcados() {
           accesoDAO.eliminarAccesoServidor();
    }
*/
}
