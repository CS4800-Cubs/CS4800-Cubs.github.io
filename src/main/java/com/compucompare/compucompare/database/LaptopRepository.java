package com.compucompare.compucompare.database;

import com.compucompare.compucompare.computerType.Laptop;

import org.springframework.data.repository.CrudRepository;

/**
 * Laptop Repository - Store & Retrieve Laptop Objects From Database
 * Automatically Implemented By Spring Data
 */
public interface LaptopRepository extends CrudRepository<Laptop, Integer>
{
    boolean existsByBrandAndModel(String brand, String model);

    Laptop findByModel(String model);

    Laptop findByBrand(String brand);

    Laptop findByProcessor(int cpuId);

    Laptop findByGraphics(int graphicsId);

    Laptop findByRam(int ramId);

    Laptop findByDisplay(int displayId);

    Laptop findByBattery(int batteryId);

    Laptop findByBrandOrModel(String brand, String model);

    Iterable<Laptop> findAll();
}