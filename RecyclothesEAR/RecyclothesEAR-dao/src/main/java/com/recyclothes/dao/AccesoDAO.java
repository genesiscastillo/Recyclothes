package com.recyclothes.dao;

import com.recyclothes.dao.entity.Acceso;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Cesar on 17-08-2016.
 */
@Local
public interface AccesoDAO {

    Acceso generarAccesoUsuario(Acceso acceso);

    void marcarAccesoUsuario(Acceso acceso);

    void eliminarAccesoServidor();

    List<Acceso> obtenerListaAccesoActualizar();
}
