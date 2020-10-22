package com.compucompare.compucompare.database;

import com.compucompare.compucompare.components.NetworkComponent;
import com.compucompare.compucompare.components.StorageComponent;
import com.compucompare.compucompare.computerType.Laptop;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Laptop Repository - Store & Retrieve Laptop Objects From Database
 * Automatically Implemented By Spring Data
 */
public interface LaptopRepository extends CrudRepository<Laptop, Integer>
{
    Laptop findByModel(String model);

    Laptop findByBrand(String brand);

    Laptop findByCPUComponent(String cpu);

    Laptop findByGPUComponent(String graphics);

    Laptop findByRAMComponent(String ram);

    Laptop findByStorage(Set<StorageComponent> storage);

    Laptop findByNetwork(Set<NetworkComponent> interfaces);

    Laptop findByDisplayComponent(String display);

    Laptop findByBatteryComponent(String battery);

    Laptop findByComponent(String component);

    Laptop findByBrandOrModel(String brand, String model);

}