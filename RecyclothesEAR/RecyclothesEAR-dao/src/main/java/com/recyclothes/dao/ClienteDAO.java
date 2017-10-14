package com.recyclothes.dao;


import com.recyclothes.dao.entity.Cliente;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Cesar on 25-02-2016.
 */
@Local
public interface ClienteDAO {

    Cliente validarAcceso(String email);
    Cliente registrarCliente(Cliente cliente);
    Boolean validarConfirmacionToken(String token);
    Cliente recuperarClaveUsuario(String email);
    Cliente actualizarCliente(Cliente cliente);
    Cliente obtenerCliente(Long idCliente);
    List<Cliente> obtenerListaCliente(String nombres, String correo, String telefono);
}
