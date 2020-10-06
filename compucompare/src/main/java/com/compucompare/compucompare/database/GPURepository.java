package com.compucompare.compucompare.database;

import com.compucompare.compucompare.components.GPUComponent;

import org.springframework.data.repository.CrudRepository;

public interface GPURepository extends CrudRepository<GPUComponent, Integer>
{
    // Auto Implemented, Use @Autowire
}