package com.compucompare.compucompare.database;

import com.compucompare.compucompare.components.BatteryComponent;

import org.springframework.data.repository.CrudRepository;

/**
 * Battery Repository - Store & Retrieve BatteryComponent Objects From Database
 * Automatically Implemented By Spring Data
 */
public interface BatteryRepository extends CrudRepository<BatteryComponent, Integer>
{
    // Use findMatch()
    BatteryComponent findByBrandAndModelAndExpectedLifeAndCapacity(String brand, String model,
                                                                   double expectedLife, int capacity);

    default BatteryComponent findMatch(String brand, String model, double expectedLife, int capacity)
    {
        return findByBrandAndModelAndExpectedLifeAndCapacity(brand, model, expectedLife, capacity);
    }
}