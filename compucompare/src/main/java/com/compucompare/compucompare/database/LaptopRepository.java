package com.compucompare.compucompare.database;

import com.compucompare.compucompare.computerType.Laptop;

import org.springframework.data.repository.CrudRepository;

/**
 * Laptop Repository - Store & Retrieve Laptop Objects From Database
 * Automatically Implemented By Spring Data
 */
public interface LaptopRepository extends CrudRepository<Laptop, Integer>
{
    Laptop findByModel(String model);
}