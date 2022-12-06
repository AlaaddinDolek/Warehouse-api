package com.warehouse.warehouse.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.warehouse.warehouse.persistence.model.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

    List<Client> findAll();

    Optional<Client> findByClientName(String clientName);

    List<Client> findByClientLocation(String location);

    Optional<Client> findByClientId(Long clientId);

}
