package com.compucompare.compucompare.network;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.compucompare.compucompare.components.BatteryComponent;
import com.compucompare.compucompare.components.CPUComponent;
import com.compucompare.compucompare.components.DisplayComponent;
import com.compucompare.compucompare.components.GPUComponent;
import com.compucompare.compucompare.components.NetworkComponent;
import com.compucompare.compucompare.components.RAMComponent;
import com.compucompare.compucompare.components.StorageComponent;
import com.compucompare.compucompare.computerType.Laptop;
import com.compucompare.compucompare.database.BatteryRepository;
import com.compucompare.compucompare.database.CPURepository;
import com.compucompare.compucompare.database.DisplayRepository;
import com.compucompare.compucompare.database.GPURepository;
import com.compucompare.compucompare.database.LaptopRepository;
import com.compucompare.compucompare.database.NetworkRepository;
import com.compucompare.compucompare.database.RAMRepository;
import com.compucompare.compucompare.database.StorageRepository;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class LaptopSource implements DataSource
{
    @Autowired
    LaptopRepository laptopRepository;

    @Autowired
    CPURepository cpuRepository;

    @Autowired
    GPURepository gpuRepository;

    @Autowired
    RAMRepository ramRepository;

    @Autowired
    StorageRepository storageRepository;

    @Autowired
    NetworkRepository networkRepository;

    @Autowired
    DisplayRepository displayRepository;

    @Autowired
    BatteryRepository batteryRepository;

    /**
     * Fetches laptops from the external source and
     * uses the results to update the database.
     */
    @Override
    public void updateDatabase()
    {
        Set<LaptopListing> listings = fetchLaptopListings();
        for (LaptopListing listing : listings)
        {
            // Check If Laptop Is Already In Database
            if (laptopRepository.existsByBrandAndModel(listing.brand, listing.model))
            {
                continue;
            }
            // Make Sure CPU Benchmark Exists For Listed CPU
            CPUComponent cpu = cpuRepository.findByBrandAndModel(listing.cpuBrand, listing.cpuModel);
            if (cpu == null)
            {
                System.out.println("No Benchmark Available For CPU: " + listing.cpuModel);
                continue;
            }
            // Make Sure GPU Benchmark Exists For Listed GPU
            GPUComponent gpu = gpuRepository.findByBrandAndModel(listing.gpuBrand, listing.gpuModel);
            if (gpu == null)
            {
                System.out.println("No Benchmark Available For GPU: " + listing.gpuModel);
                continue;
            }
            // With Primary Benchmarks Available, Can Create Laptop
            Laptop laptop = new Laptop();
            laptop.setBrand(listing.brand);
            laptop.setModel(listing.model);
            laptop.setDisplayName(listing.displayName);
            laptop.setThumnailUrl(listing.thumbnailUrl);
            laptop.setPageUrl(listing.pageUrl);
            laptop.setProcessor(cpu);
            laptop.setGraphics(gpu);
            // Set RAM, Linking Existing Database Entry If Possible
            RAMComponent ram = ramRepository.findMatch(listing.ramBrand, listing.ramModel, listing.ramAmount,
                                                       listing.ramSpeed, listing.dualChannelRam);
            if (ram == null)
            {
                ram = new RAMComponent(listing.ramBrand, listing.ramModel, listing.ramAmount,
                                       listing.ramSpeed, listing.dualChannelRam);
            }
            laptop.setRam(ram);
            // Set Storage, Linking Existing Database Entries If Possible
            for (StorageListing storageListing : listing.storageListings)
            {
                StorageComponent storage = storageRepository.findMatch(storageListing.brand, storageListing.model,
                                                                       storageListing.capacity, storageListing.isSolidState, storageListing.isNvme);
                if (storage == null)
                {
                    storage = new StorageComponent(storageListing.brand, storageListing.model,
                                                   storageListing.capacity, storageListing.isSolidState, storageListing.isNvme);
                }
                laptop.addStorage(storage);
            }
            // Set Network Interfaces, Linking Existing Database Entries If Possible
            for (NetworkListing networkListing : listing.networkListings)
            {
                NetworkComponent network = networkRepository.findMatch(networkListing.brand, networkListing.model, networkListing.maxSpeed,
                                                                       networkListing.isWireless);
                if (network == null)
                {
                    network = new NetworkComponent(networkListing.brand, networkListing.model, networkListing.maxSpeed,
                                                   networkListing.isWireless, networkListing.standards);
                }
                laptop.addInterface(network);
            }
            // Set Display, Linking Existing Database Entry If Possible
            DisplayComponent display = displayRepository.findMatch(listing.displayBrand, listing.displayModel, listing.displaySize,
                                                                   listing.resX, listing.resY, listing.refreshRate);
            if (display == null)
            {
                display = new DisplayComponent(listing.displayBrand, listing.displayModel, listing.displaySize,
                                               listing.resX, listing.resY, listing.refreshRate);
            }
            laptop.setDisplay(display);
            // Set Battery, Linking Existing Database Entry If Possible
            BatteryComponent battery = batteryRepository.findMatch(listing.batteryBrand, listing.batteryModel,
                                                                   listing.expectedBatteryLife, listing.batteryCapacity);
            if (battery == null)
            {
                battery = new BatteryComponent(listing.batteryBrand, listing.batteryModel,
                                               listing.expectedBatteryLife, listing.batteryCapacity);
            }
            laptop.setBattery(battery);
            laptopRepository.save(laptop);
        }
    }

    /**
     * Creates a list of laptop objects from some
     * external source.
     * 
     * @return The list of laptop objects.
     */
    public abstract Set<LaptopListing> fetchLaptopListings();

    /**
     * Determines whether this source can parse
     * laptop information from the given URL.
     * 
     * @param laptopPageUrl the laptop page URL
     * @return true if this source can handle the URL
     */
    public abstract boolean validUrl(String laptopPageUrl);

    /**
     * Parses laptop information from the given URL.
     * 
     * @param laptopPageUrl The laptop page URL.
     * @return a LaptopListing containing the parsed information.
     */
    public abstract LaptopListing fetchLaptopListing(String laptopPageUrl) throws IOException;

    /**
     * Listing classes hold data parsed from websites
     * in a standard format so that a generic function
     * can use the results to update the database.
     */
    class LaptopListing
    {
        String brand;
        String model;
        String displayName;
        String thumbnailUrl;
        String pageUrl;
        String cpuBrand;
        String cpuModel;
        String gpuBrand;
        String gpuModel;
        String ramBrand;
        String ramModel;
        double ramAmount;
        int ramSpeed;
        boolean dualChannelRam;
        Set<StorageListing> storageListings;
        Set<NetworkListing> networkListings;
        String displayBrand;
        String displayModel;
        double displaySize;
        int resX;
        int resY;
        int refreshRate;
        String batteryBrand;
        String batteryModel;
        double expectedBatteryLife;
        int batteryCapacity;

        LaptopListing()
        {
            brand = "Unknown Brand";
            model = "Unknown Model";
            displayName = "Unknown";
            thumbnailUrl = "img/cc_laptop.png";
            pageUrl = "";
            cpuBrand = "Unknown Brand";
            cpuModel = "Unknown Model";
            gpuBrand = "Unknown Brand";
            gpuModel = "Unknown Model";
            ramBrand = "Unknown Brand";
            ramModel = "Unknown Model";
            ramAmount = 0.0;
            ramSpeed = 0;
            dualChannelRam = false;
            storageListings = new HashSet<>();
            networkListings = new HashSet<>();
            displayBrand = "Unknown Brand";
            displayModel = "Unknown Model";
            resX = 0;
            resY = 0;
            refreshRate = 0;
            batteryBrand = "Unknown Brand";
            batteryModel = "Unknown Model";
            expectedBatteryLife = 0.0;
            batteryCapacity = 0;
        }
    }

    class StorageListing
    {
        String brand;
        String model;
        double capacity;
        boolean isSolidState;
        boolean isNvme;
        
        StorageListing()
        {
            brand = "Unknown Brand";
            model = "Unknown Model";
            capacity = 0;
            isSolidState = false;
            isNvme = false;
        }
    }

    class NetworkListing
    {
        String brand;
        String model;
        int maxSpeed;
        boolean isWireless;
        Set<String> standards;
        
        NetworkListing()
        {
            brand = "Unknown Brand";
            model = "Unknown Model";
            maxSpeed = 0;
            isWireless = false;
            standards = new HashSet<>();
        }
    }
}