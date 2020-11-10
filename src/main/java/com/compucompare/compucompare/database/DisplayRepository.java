package com.compucompare.compucompare.database;

import com.compucompare.compucompare.components.DisplayComponent;

import org.springframework.data.repository.CrudRepository;

/**
 * Display Repository - Store & Retrieve DisplayComponent Objects From Database
 * Automatically Implemented By Spring Data
 */
public interface DisplayRepository extends CrudRepository<DisplayComponent, Integer>
{
    // Use findMatch()
    DisplayComponent findByBrandAndModelAndSizeAndResXAndResYAndRefreshRate(String brand, String model, double size,
                                                                            int resX, int resY, int refreshRate);

    default DisplayComponent findMatch(String brand, String model, double size,
                                       int resX, int resY, int refreshRate)
    {
        return findByBrandAndModelAndSizeAndResXAndResYAndRefreshRate(brand, model, size, resX, resY, refreshRate);
    }
}