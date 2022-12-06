package com.warehouse.warehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.warehouse.exceptions.ClientAlreadyExistsException;
import com.warehouse.warehouse.exceptions.ClientNotFoundException;
import com.warehouse.warehouse.exceptions.LocationNotFoundException;
import com.warehouse.warehouse.management.ClientManager;
import com.warehouse.warehouse.persistence.model.Client;
import com.warehouse.warehouse.persistence.model.Dtos.ClientDto;

@Service
public class ClientService {

    @Autowired
    private ClientManager clientManager;

    public Long createClient(String clientName, String location) throws ClientAlreadyExistsException {
        return clientManager.createClient(clientName, location);
    }

    public List<ClientDto> getAllClients() {
        return clientManager.clientListToClientDtoList(clientManager.getAllClients());
    }

    public ClientDto getClientByName(String clientName) throws ClientNotFoundException {
        return clientManager.getByClientName(clientName).toClientDto();
    }

    public void deleteClientById(Long clientId) throws ClientNotFoundException {
        clientManager.deleteClientById(clientId);
    }

    public List<ClientDto> getClientsByLocation(String clientLocation) throws LocationNotFoundException {
        return clientManager.clientListToClientDtoList(clientManager.getByClientLocation(clientLocation));
    }

    public ClientDto updateClientById(Client client) throws ClientNotFoundException, ClientAlreadyExistsException {
        return clientManager.updateClientById(client).toClientDto();
    }

    public ClientDto getClientById(Long clientId) throws ClientNotFoundException {
        return clientManager.getByClientId(clientId).toClientDto();
    }

}
