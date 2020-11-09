package com.compucompare.compucompare.network;

public interface DataSource
{
    /**
     * Fetches data from an external source and
     * uses the results to update the database.
     */
    public void updateDatabase();
}