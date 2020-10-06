package com.compucompare.compucompare;

import java.util.HashSet;
import java.util.Set;

import com.compucompare.compucompare.components.BatteryComponent;
import com.compucompare.compucompare.components.CPUComponent;
import com.compucompare.compucompare.components.DisplayComponent;
import com.compucompare.compucompare.components.GPUComponent;
import com.compucompare.compucompare.components.RAMComponent;
import com.compucompare.compucompare.components.StorageComponent;
import com.compucompare.compucompare.components.NetworkComponent;
import com.compucompare.compucompare.computerType.Laptop;
import com.compucompare.compucompare.database.LaptopRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner
{
    /**
     * Start the Spring application.
     * 
     * @param args Command-Line Arguments
     */
    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private LaptopRepository laptopRepository;

    /**
     * Creates an example laptop and stores
     * it into the database for testing.
     * Will be removed soon.
     * 
     * @param args Command-Line Arguments
     */
    @Override
    public void run(String... args)
    {
        Set<StorageComponent> drives = new HashSet<>();
        drives.add(new StorageComponent("Samsung", "EVO", 256, true, true));
        drives.add(new StorageComponent("WD", "Blue", 1000, false, false));
        Set<NetworkComponent> interfaces = new HashSet<>();
        Set<String> supportedWirelessStandards = new HashSet<>();
        supportedWirelessStandards.add("BT");
        supportedWirelessStandards.add("802.11a");
        supportedWirelessStandards.add("802.11b");
        supportedWirelessStandards.add("802.11g");
        supportedWirelessStandards.add("802.11n");
        supportedWirelessStandards.add("802.11ac");
        supportedWirelessStandards.add("802.11ax");
        interfaces.add(new NetworkComponent("Intel", "AX200", 2400, true, supportedWirelessStandards));
        Laptop testLaptop = new Laptop("HP", "dsuyf7tud",
            new CPUComponent("Intel", "9750H", 1000, 2000, 4, "x86"),
            new GPUComponent("Nvidia", "GTX 1660Ti Mobile", 1000),
            new RAMComponent("Crucial", "Ballistix", 16, 2400, true),
            drives,
            interfaces,
            new DisplayComponent("HP", "8743yevfn", 15, 1920, 1080, 55),
            new BatteryComponent("Unknown", "Unknown", 5.5, 5000));
        laptopRepository.save(testLaptop);
    }
}