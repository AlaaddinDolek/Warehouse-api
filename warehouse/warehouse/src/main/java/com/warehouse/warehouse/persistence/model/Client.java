package com.warehouse.warehouse.persistence.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.warehouse.warehouse.persistence.model.Dtos.ClientDto;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    private String clientName;

    @OneToOne(fetch = FetchType.LAZY)
    private PurchaseProduct purchaseProduct;

    private String clientLocation;

    public Client() {
    }

    public ClientDto toClientDto() {
        ClientDto clientDto = new ClientDto();
        clientDto.setClientName(clientName);
        clientDto.setClientLocation(clientLocation);
        return clientDto;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
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
