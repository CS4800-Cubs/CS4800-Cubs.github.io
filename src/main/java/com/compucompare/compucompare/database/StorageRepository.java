package com.compucompare.compucompare.database;

import com.compucompare.compucompare.components.StorageComponent;

import org.springframework.data.repository.CrudRepository;

/**
 * Storage Repository - Store & Retrieve StorageComponent Objects From Database
 * Automatically Implemented By Spring Data
 */
public interface StorageRepository extends CrudRepository<StorageComponent, Integer>
{
    // Use findMatch()
    StorageComponent findByBrandAndModelAndCapacityAndSolidStateAndNvme(String brand, String model, double capacity,
                                                                        boolean solidState, boolean nvme);

    default StorageComponent findMatch(String brand, String model, double capacity, boolean solidState, boolean nvme)
    {
        return findByBrandAndModelAndCapacityAndSolidStateAndNvme(brand, model, capacity, solidState, nvme);
    }
}