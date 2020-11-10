package com.compucompare.compucompare.database;

import com.compucompare.compucompare.components.RAMComponent;

import org.springframework.data.repository.CrudRepository;

/**
 * RAM Repository - Store & Retrieve RAMComponent Objects From Database
 * Automatically Implemented By Spring Data
 */
public interface RAMRepository extends CrudRepository<RAMComponent, Integer>
{
    // Use findMatch()
    RAMComponent findByBrandAndModelAndMemoryAndSpeedAndDualChannel(String brand, String model,
                                                                    double memory, int speed, boolean dualChannel);

    default RAMComponent findMatch(String brand, String model, double memory, int speed, boolean dualChannel)
    {
        return findByBrandAndModelAndMemoryAndSpeedAndDualChannel(brand, model, memory, speed, dualChannel);
    }
}