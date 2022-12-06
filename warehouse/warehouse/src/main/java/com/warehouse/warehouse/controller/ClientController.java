package com.warehouse.warehouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.warehouse.exceptions.ClientAlreadyExistsException;
import com.warehouse.warehouse.exceptions.ClientNotFoundException;
import com.warehouse.warehouse.exceptions.LocationNotFoundException;
import com.warehouse.warehouse.persistence.model.Client;
import com.warehouse.warehouse.persistence.model.Dtos.ClientDto;
import com.warehouse.warehouse.service.ClientService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public Long createClient(@RequestParam String clientName, String location) throws ClientAlreadyExistsException {
        return clientService.createClient(clientName, location);
    }

    @GetMapping
    public List<ClientDto> getAllClients() {
        return clientService.getAllClients();
    }

    @DeleteMapping
    public void deleteClientById(@RequestParam Long clientId) throws ClientNotFoundException {
        clientService.deleteClientById(clientId);
    }

    @PutMapping
    public ClientDto updateClient(@RequestBody Client client)
            throws ClientNotFoundException, ClientAlreadyExistsException {
        return clientService.updateClientById(client);
    }

    @GetMapping("/byName")
    public ClientDto getClientByName(@RequestParam String clientName) throws ClientNotFoundException {
        return clientService.getClientByName(clientName);
    }

    @GetMapping("/byLocation")
    public List<ClientDto> getClientsByLocation(@RequestParam String clientLocation) throws LocationNotFoundException {
        return clientService.getClientsByLocation(clientLocation);
    }

    @GetMapping("/byId")
    public ClientDto getClientById(@RequestParam Long clientId) throws ClientNotFoundException {
        return clientService.getClientById(clientId);
    }

}
