package com.compucompare.compucompare;

import com.compucompare.compucompare.comparison.WeightedComparator;
import com.compucompare.compucompare.comparison.WeightedPreferences;
import com.compucompare.compucompare.components.*;
import com.compucompare.compucompare.computerType.Computer;
import com.compucompare.compucompare.database.ComputerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class MainController
{
    @Autowired
    private ComputerRepository laptopRepository;

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
    Computer getModel(@RequestParam(value = "model", defaultValue = "") String model)
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
     * @param maxRam An double value that lists max ram
     * @param minRam An double value that lists minimum ram value
     * @param minStorage An double value that lists min. amount of storage
     * @param maxStorage An double value that lists max amount of storage
     * @param display Int that tells wanted display size.
     * @return A list of Laptop/Computer objects
     */
    @RequestMapping("/generalSearch")
    public Set<SearchResult> getByFilter(@RequestParam(value = "query", defaultValue = "") String query,
                                      @RequestParam(value = "portable", defaultValue = "") String portable,
                                      @RequestParam(value = "brand", defaultValue = "") String brand,
                                      @RequestParam(value = "cpu", defaultValue = "") String cpu,
                                      @RequestParam(value = "graphics", defaultValue = "") String graphics,
                                      @RequestParam(value = "minRam", defaultValue = "0.0") double minRam,
                                      @RequestParam(value = "maxRam", defaultValue = "0.0") double maxRam,
                                      @RequestParam(value = "minStorage", defaultValue = "0.0") double minStorage,
                                      @RequestParam(value = "maxStorage", defaultValue = "0.0") double maxStorage,
                                      @RequestParam(value = "display", defaultValue = "0.0") double display){
        Iterable<Computer> computers = laptopRepository.findAll();

        Set<SearchResult> results = new TreeSet<>();
        int minRelScore = 1;

        for (Computer laptop : computers) {
            if (!checkFilters(laptop, brand, cpu, graphics, minRam, maxRam, minStorage,
                    maxStorage, display))
                continue;
            int relevanceScore = 0;
            query = query.toLowerCase();
            if (query.contains(laptop.getBrand().toLowerCase()))
                relevanceScore++;
            if (query.contains(laptop.getModel().toLowerCase()))
                relevanceScore++;
            if (query.contains(laptop.getProcessor().getBrand().toLowerCase()))
                relevanceScore++;
            if (query.contains(laptop.getProcessor().getModel().toLowerCase()))
                relevanceScore++;
            if (query.contains(laptop.getGraphics().getBrand().toLowerCase()))
                relevanceScore++;
            if (query.contains(laptop.getGraphics().getModel().toLowerCase()))
                relevanceScore++;
            if (query.contains(laptop.getDisplay().getBrand().toLowerCase()))
                relevanceScore++;
            if (relevanceScore >= minRelScore)
                results.add(new SearchResult(laptop, relevanceScore));
        }
        return results;

    }

    public boolean checkFilters(Computer laptop, String brand, String cpu,
                                String graphics, double minRam, double maxRam, double minStorage,
                                double maxStorage, double display){
        Set<StorageComponent> storageSet = laptop.getStorage();
        // Check if it's default value, then continue
        if (!laptop.getBrand().equals(brand) && !brand.isEmpty())
            return false;
        if(!(laptop.getRam().getMemory() >= minRam) && !(laptop.getRam().getMemory() <= maxRam)
            && minRam != 0.0 && maxRam != 0.0)
            return false;
        if(!laptop.getProcessor().getBrand().contains(cpu) && !cpu.isEmpty())
            return false;
        if(!laptop.getProcessor().getModel().contains(cpu) && !cpu.isEmpty())
            return false;
        if(!laptop.getGraphics().getBrand().contains(graphics) && !graphics.isEmpty())
            return false;
        double storageTotal = getStorageTotal(storageSet);
        if(!(storageTotal > minStorage) && !(storageTotal < maxStorage)
                && minStorage != 0.0 && maxStorage != 0.0)
            return false;
        if(laptop.getDisplay().getSize() != display && display != 0.0)
            return false;

        return true;
    }

    public double getStorageTotal(Set<StorageComponent> set){
        double totalStorage = 0;
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
    public SurveyResponse getByFilter(@RequestBody SurveyResponse results){

        return results;
    }

    @RequestMapping("/compare")
    public Set<Computer> compareComputers(@RequestParam(value = "ids") Set<Integer> ids)
    {
        WeightedPreferences weights = new WeightedPreferences();
        WeightedComparator<Computer> comparator = new WeightedComparator<>(weights);
        Set<Computer> comparedComputers = new TreeSet<>(comparator);
        Iterable<Computer> selected = laptopRepository.findAllById(ids);
        for (Computer computer : selected)
        {
            comparedComputers.add(computer);
        }
        return comparedComputers;
    }

    /**
     * This function will act as a temporary request to allow UI team to progress
     *
     * @return A JSON object.
     */

    @RequestMapping("/tempSearch")
    public Computer returnTempResults() {
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
        Computer testLaptop = new Computer("HP", "dsuyf7tud", "HP Laptop", "thumbnailUrl", "pageUrl", true,
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