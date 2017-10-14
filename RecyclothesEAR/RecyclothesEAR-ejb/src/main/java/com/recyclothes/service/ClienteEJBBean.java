package com.recyclothes.service;

import com.recyclothes.dao.ClienteDAO;
import com.recyclothes.ejbclient.ClienteEJB;

/**
 * Created by Cesar on 18-07-2016.
 */
@Stateless
@Remote(ClienteEJB.class)
public class ClienteEJBBean implements ClienteEJB {

    @EJB
    ClienteDAO clienteDAO;

/*
    @Override
    public ClienteDTO validarAcceso(String email) {
        return null;
    }

    @Override
    public ClienteDTO registrarCliente(ClienteDTO cliente) {
        return null;
    }

    @Override
    public Boolean validarConfirmacionToken(String token) {
        return null;
    }

    @Override
    public ClienteDTO recuperarClaveUsuario(String email) {
        return null;
    }

    @Override
    @Asynchronous
    public void actualizarTelefonoCliente(ClienteDTO clienteDTO) {
        Cliente cliente = clienteDAO.obtenerCliente(clienteDTO.getId());
        cliente.setTelefono(clienteDTO.getTelefono());
        clienteDAO.actualizarCliente(cliente);
    }

    @Override
    public List<ClienteDTO> obtenerListaCliente() {
        List<ClienteDTO> clienteDTOs = new ArrayList<>();
        List<Cliente> clienteList = clienteDAO.obtenerListaCliente();
        for(Cliente cliente : clienteList){
            ClienteDTO clienteDTO = Translation.convertToDTO(cliente);
            clienteDTOs.add(clienteDTO);
        }
        return clienteDTOs;
    }
*/
}
