package com.compucompare.compucompare.database;

import com.compucompare.compucompare.computerType.Computer;

import org.springframework.data.repository.CrudRepository;

/**
 * Computer Repository - Store & Retrieve Computer Objects From Database
 * Automatically Implemented By Spring Data
 */
public interface ComputerRepository extends CrudRepository<Computer, Integer>
{
    boolean existsByBrandAndModel(String brand, String model);

    Computer findByModel(String model);

    Computer findByBrand(String brand);

    Computer findByProcessor(int cpuId);

    Computer findByGraphics(int graphicsId);

    Computer findByRam(int ramId);

    Computer findByDisplay(int displayId);

    Computer findByBattery(int batteryId);

    Computer findByBrandOrModel(String brand, String model);

    Iterable<Computer> findAll();
}