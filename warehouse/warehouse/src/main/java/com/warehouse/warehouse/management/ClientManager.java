package com.warehouse.warehouse.management;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.warehouse.exceptions.ClientAlreadyExistsException;
import com.warehouse.warehouse.exceptions.ClientNotFoundException;
import com.warehouse.warehouse.exceptions.LocationNotFoundException;
import com.warehouse.warehouse.persistence.model.Client;
import com.warehouse.warehouse.persistence.model.Dtos.ClientDto;
import com.warehouse.warehouse.persistence.repository.ClientRepository;

@Service
public class ClientManager {

    @Autowired
    private ClientRepository clientRepository;

    public Client getByClientId(Long id) throws ClientNotFoundException {
        Optional<Client> client = clientRepository.findByClientId(id);
        if (!client.isPresent())
            throw new ClientNotFoundException("Client not found");

        return client.get();
    }

    public Client getByClientName(String clientName) throws ClientNotFoundException {
        Optional<Client> client = clientRepository.findByClientName(clientName);
        if (!client.isPresent())
            throw new ClientNotFoundException("Client not found");

        return client.get();
    }

    public List<Client> getByClientLocation(String location) throws LocationNotFoundException {
        List<Client> clientList = clientRepository.findByClientLocation(location);
        if (clientList.isEmpty())
            throw new LocationNotFoundException("Location not found");

        return clientList;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client updateClientById(Client client) throws ClientNotFoundException, ClientAlreadyExistsException {

        Client updatedClient = getByClientId(client.getClientId());

        Optional<Client> optionalNewClientName = clientRepository.findByClientName(client.getClientName());
        if (optionalNewClientName.isPresent())
            throw new ClientAlreadyExistsException("Client already exists");

        updatedClient.setClientLocation(client.getClientLocation());
        updatedClient.setClientName(client.getClientName());
        updatedClient.setClientId(client.getClientId());

        return clientRepository.save(updatedClient);
    }

    public void deleteClientById(Long clientId) throws ClientNotFoundException {
        getByClientId(clientId);
        clientRepository.deleteById(clientId);
    }

    public Long createClient(String clientName, String location) throws ClientAlreadyExistsException {

        Optional<Client> optionalClient = clientRepository.findByClientName(clientName);
        if (optionalClient.isPresent())
            throw new ClientAlreadyExistsException("Client already exists");

        Client client = new Client();
        client.setClientName(clientName);
        client.setClientLocation(location);

        clientRepository.save(client);

        return client.getClientId();
    }

    public List<ClientDto> clientListToClientDtoList(List<Client> clientList) {
        List<ClientDto> clientDtoList = new ArrayList<>();

        for (Client client : clientList) {
            clientDtoList.add(client.toClientDto());
        }
        return clientDtoList;
    }

}
