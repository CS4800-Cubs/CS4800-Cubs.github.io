package com.compucompare.compucompare.network;

import java.util.Set;

import com.compucompare.compucompare.computerType.Laptop;

public interface LaptopSource
{
    /**
     * Creates a list of laptop objects from some
     * external source.
     * 
     * @return The list of laptop objects.
     */
    public Set<Laptop> getAllLaptops();
}