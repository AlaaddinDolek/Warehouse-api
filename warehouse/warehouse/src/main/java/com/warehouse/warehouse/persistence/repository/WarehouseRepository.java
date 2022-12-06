package com.warehouse.warehouse.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.warehouse.warehouse.persistence.model.Warehouse;

public interface WarehouseRepository extends CrudRepository<Warehouse, Long> {

    List<Warehouse> findAll();

    Optional<Warehouse> findWarehouseByLocation(String location);

    Optional<Warehouse> findWarehouseByWarehouseId(Long warehouseId);

    List<Warehouse> findByTotalCapacityTonGreaterThanEqual(Double ton);

    List<Warehouse> findByTotalCapacityTonLessThanEqual(Double ton);

    List<Warehouse> findByRegion(String region);

}
