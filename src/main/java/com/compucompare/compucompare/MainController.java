package com.compucompare.compucompare;

import com.compucompare.compucompare.components.*;
import com.compucompare.compucompare.computerType.Computer;
import com.compucompare.compucompare.computerType.Laptop;
import com.compucompare.compucompare.database.LaptopRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class MainController
{
    @Autowired
    private LaptopRepository laptopRepository;

    /**
     * Gets a laptop by model.
     * 
     * Only for testing purposes, the client will
     * give a search query where we will give a list
     * of laptops rather than a single laptop.
     * After the client has the list they can
     * get the laptop by database ID instead.
     *
     * @param model The model of the laptop.
     * @return A laptop object.
     */
    @RequestMapping("/getModel")
    Laptop getModel(@RequestParam(value = "model", defaultValue = "") String model)
    {
        return laptopRepository.findByModel(model);
    }




    /**
     * Get Laptop or Computer based on filtered searches and searchbar searches
     *
     * @param query A string that the user has entered into the search bar
     * @param brand A string denotes the brand the user wishes to see
     * @param cpu   A string that says the cpu brand
     * @param graphics A string that tells the graphics brand
     * @param maxRam An int value that lists max ram
     * @param minRam An int value that lists minimum ram value
     * @param minStorage An int value that lists min. amount of storage
     * @param maxStorage An int value that lists max amount of storage
     * @param display Int that tells wanted display size.
     * @return A list of Laptop/Computer objects
     */
    @RequestMapping("/generalSearch")
    public List<Computer> getByFilter(@RequestParam(value = "query") String query,
                                      @RequestParam(value = "brand") String brand,
                                      @RequestParam(value = "cpu") String cpu,
                                      @RequestParam(value = "graphics") String graphics,
                                      @RequestParam(value = "minRam") int minRam,
                                      @RequestParam(value = "maxRam") int maxRam,
                                      @RequestParam(value = "minStorage") int minStorage,
                                      @RequestParam(value = "maxStorage") int maxStorage,
                                      @RequestParam(value = "display") int display){
    Iterable<Laptop> laptops = laptopRepository.findAll();
    List<Computer> results = new ArrayList<>();
    int minRelScore = 0;
    for(Laptop laptop: laptops){
        if (!checkFilters(laptop, brand, cpu, graphics, minRam, maxRam, minStorage,
                maxStorage, display))
            continue;
        int relevanceScore = 0;
        if(laptop.getBrand().contains(query))
            relevanceScore++;
        if(laptop.getModel().equals(query))
            relevanceScore++;
        if(laptop.getProcessor().getBrand().contains(query))
            relevanceScore++;
        if(laptop.getProcessor().getModel().contains(query))
            relevanceScore++;
        if(laptop.getGraphics().getBrand().contains(query))
            relevanceScore++;
        if(laptop.getGraphics().getModel().contains(query))
            relevanceScore++;
        if(laptop.getDisplay().getBrand().contains(query))
            relevanceScore++;
        if(relevanceScore >= minRelScore)
            results.add(laptop);
    }
    return results;

    }

    public boolean checkFilters(Laptop laptop, String brand, String cpu,
                                String graphics, int minRam, int maxRam, int minStorage,
                                int maxStorage, int display){
        Set<StorageComponent> storageSet = laptop.getStorage();
        if(!laptop.getBrand().equals(brand))
            return false;
        if(!(laptop.getRam().getMemory() >= minRam) && !(laptop.getRam().getMemory() <= maxRam))
            return false;
        if(!laptop.getProcessor().getBrand().contains(cpu))
            return false;
        if(!laptop.getProcessor().getModel().contains(cpu))
            return false;
        if(!laptop.getGraphics().getBrand().contains(graphics))
            return false;
        int storageTotal = getStorageTotal(storageSet);
        if(!(storageTotal > minStorage) && !(storageTotal < maxStorage))
            return false;
        if(laptop.getDisplay().getSize() != display)
            return false;

        return true;
    }

    public int getStorageTotal(Set<StorageComponent> set){
        int totalStorage = 0;
        for(StorageComponent storage: set)
            totalStorage += storage.getCapacity();
        return totalStorage;
    }

    /**
     * Get Laptop or Computer based on filtered searches
     *
     * @param results A JSONObject that lists the survey results
     * @return A Laptop/Computer object
     */
    @RequestMapping("/surveySearch")
    public List<Computer> getByFilter(@RequestParam(value = "results") JSONObject results){


        return null;
    }

    /**
     * This function will act as a temporary request to allow UI team to progress
     *
     * @return A JSON object.
     */

    @RequestMapping("/tempSearch")
    public Laptop returnTempResults() {
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
        Laptop testLaptop = new Laptop("HP", "dsuyf7tud", "HP Laptop", "thumbnailUrl", "pageUrl",
                new CPUComponent("Intel", "9750H", 1000, 2000, 4, "x86"),
                new GPUComponent("Nvidia", "GTX 1660Ti Mobile", 1000),
                new RAMComponent("Crucial", "Ballistix", 16, 2400, true),
                drives,
                interfaces,
                new DisplayComponent("HP", "8743yevfn", 15, 1920, 1080, 55),
                new BatteryComponent("Unknown", "Unknown", 5.5, 5000));
        return testLaptop;
    }

}