package com.compucompare.compucompare.database;

import com.compucompare.compucompare.components.CPUComponent;

import org.springframework.data.repository.CrudRepository;

/**
 * CPU Repository - Store & Retrieve CPUComponent Objects From Database
 * Automatically Implemented By Spring Data
 */
public interface CPURepository extends CrudRepository<CPUComponent, Integer>
{
    CPUComponent findByBrandAndModel(String brand, String model);
}