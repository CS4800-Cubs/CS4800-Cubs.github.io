package com.compucompare.compucompare.routines;

import com.compucompare.compucompare.network.GeekbenchCPUSource;
import com.compucompare.compucompare.network.GeekbenchGPUSource;
import com.compucompare.compucompare.network.LaptopSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUpdateTask
{
    @Autowired
    GeekbenchCPUSource cpuData;

    @Autowired
    GeekbenchGPUSource gpuData;

    @Autowired
    LaptopSource laptopData;

    @Scheduled(fixedDelay = 86400000, initialDelay = 0)
    public void updateDatabase()
    {
        cpuData.updateDatabase();
        gpuData.updateDatabase();
        laptopData.updateDatabase();
    }
}