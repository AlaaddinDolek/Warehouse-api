package com.warehouse.warehouse.persistence.model.Dtos;

import com.warehouse.warehouse.persistence.model.Client;

public class ClientDto {

    private String clientName;

    private String clientLocation;

    public ClientDto() {

    }

    public Client toClient() {
        Client client = new Client();
        client.setClientLocation(clientLocation);
        client.setClientName(clientName);

        return client;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientLocation() {
        return clientLocation;
    }

    public void setClientLocation(String clientLocation) {
        this.clientLocation = clientLocation;
    }

}
