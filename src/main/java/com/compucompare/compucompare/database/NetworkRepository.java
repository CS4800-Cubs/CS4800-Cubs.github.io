package com.compucompare.compucompare.database;

import com.compucompare.compucompare.components.NetworkComponent;

import org.springframework.data.repository.CrudRepository;

/**
 * Network Repository - Store & Retrieve NetworkComponent Objects From Database
 * Automatically Implemented By Spring Data
 */
public interface NetworkRepository extends CrudRepository<NetworkComponent, Integer>
{
    // Use findMatch()
    NetworkComponent findByBrandAndModelAndMaxSpeedAndWireless(String brand, String model, int maxSpeed,
                                                                           boolean wireless);

    default NetworkComponent findMatch(String brand, String model, int maxSpeed, boolean wireless)
    {
        return findByBrandAndModelAndMaxSpeedAndWireless(brand, model, maxSpeed, wireless);
    }
}