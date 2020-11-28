package com.compucompare.compucompare.database;

import com.compucompare.compucompare.computerType.Computer;

import java.util.List;

public interface CustomComputerRepository {
    List<Computer> findComputerByQuery(String query);
}
