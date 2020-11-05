package com.compucompare.compucompare;

import com.compucompare.compucompare.components.*;
import com.compucompare.compucompare.computerType.Computer;
import com.compucompare.compucompare.computerType.Laptop;
import com.compucompare.compucompare.database.LaptopRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * Get Laptop based on search query.
     * Currently will only use query searches to pull Laptops that have that brand or model
     *
     * @param query The query that the user is searching for
     * @return A laptop object.
     */
    @RequestMapping("/generalSearch")
    public Laptop getBySearch(@RequestParam (value = "query") String query){
        return laptopRepository.findByBrandOrModel(query, query);
    }



    /**
     * Get Laptop or Computer based on filtered searches
     *
     * @param
     * @return A Laptop/Computer object
     */
    @RequestMapping("/filterSearch")
    public List<Computer> getByFilter(@RequestParam(value = "type") String type,
                                    @RequestParam(value = "brand") String brand,
                                    @RequestParam(value = "model") String model,
                                    @RequestParam(value = "cpu") String cpu,
                                    @RequestParam(value = "graphics") String graphics,
                                    @RequestParam(value = "minRam") int minRam,
                                    @RequestParam(value = "maxRam") int maxRam,
                                    @RequestParam(value = "minStorage") int minStorage,
                                    @RequestParam(value = "maxStorage") int maxStorage,
                                    @RequestParam(value = "interface") String interfaces,
                                    @RequestParam(value = "display") int display,
                                    @RequestParam(value = "battery") String battery){
    Iterable<Laptop> laptops = laptopRepository.findAll();
    List<Computer> results = null;
    int minRelScore = 0;
    for(Laptop laptop: laptops){
        int relevanceScore = 0;
        Set<StorageComponent> storageSet = laptop.getStorage();
        if(laptop.getBrand().equals(brand))
            relevanceScore++;
        if(laptop.getModel().equals(model))
            relevanceScore++;
        if(laptop.getRam().getMemory() >= minRam && laptop.getRam().getMemory() <= maxRam)
            relevanceScore++;
        if(laptop.getProcessor().getBrand().contains(cpu))
            relevanceScore++;
        if(laptop.getProcessor().getModel().contains(cpu))
            relevanceScore++;
        if(laptop.getGraphics().getBrand().contains(graphics))
            relevanceScore++;
        if(laptop.getGraphics().getModel().contains(graphics))
            relevanceScore++;
        int storageTotal = getStorageTotal(storageSet);
        if(storageTotal > minStorage && storageTotal < maxStorage)
            relevanceScore++;
        if(laptop.getDisplay().getSize() == display)
            relevanceScore++;
        if(relevanceScore >minRelScore)
            results.add(laptop);
    }
    return results;

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
     * @param
     * @return A Laptop/Computer object
     */
    @RequestMapping("/surveySearch")
    public List<Computer> getByFilter(@RequestParam(value = "type") String type,
                                      @RequestParam(value = "activity") String activity,
                                      @RequestParam(value = "brand") String brand){

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
        Laptop testLaptop = new Laptop("HP", "dsuyf7tud",
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