package com.compucompare.compucompare.database;

import com.compucompare.compucompare.components.GPUComponent;

import org.springframework.data.repository.CrudRepository;

/**
 * GPU Repository - Store & Retrieve GPUComponent Objects From Database
 * Automatically Implemented By Spring Data
 */
public interface GPURepository extends CrudRepository<GPUComponent, Integer>
{
    GPUComponent findByBrandAndModel(String brand, String model);
}