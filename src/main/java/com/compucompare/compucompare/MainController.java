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

        Set<SearchResult> results = new TreeSet<>(Comparator.reverseOrder());
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
        
        if (!brand.isEmpty() && !laptop.getBrand().equals(brand))
            return false;
        if(minRam != 0.0 && maxRam != 0.0
            && laptop.getRam().getMemory() < minRam
            && laptop.getRam().getMemory() > maxRam)
            return false;
        if(!cpu.isEmpty() && !laptop.getProcessor().getBrand().contains(cpu))
            return false;
        if(!cpu.isEmpty() && !laptop.getProcessor().getModel().contains(cpu))
            return false;
        if(!graphics.isEmpty() && !laptop.getGraphics().getBrand().contains(graphics))
            return false;
        double storageTotal = getStorageTotal(storageSet);
        if(minStorage != 0.0 && maxStorage != 0.0 &&
            storageTotal < minStorage && storageTotal > maxStorage)
            return false;
        if(display != 0.0 && laptop.getDisplay().getSize() != display)
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
     * @param survey A JSONObject that lists the survey results
     * @return A Laptop/Computer object
     */
    @RequestMapping("/surveySearch")
    public Set<Computer> getByFilter(@RequestBody SurveyResponse survey){
        WeightedPreferences weights = new WeightedPreferences(survey);
        WeightedComparator<Computer> comparator = new WeightedComparator<>(weights);
        Set<Computer> comparedComputers = new TreeSet<>(comparator);
        Iterable<Computer> computers = laptopRepository.findAll();

        List<String> brands = survey.brands;
        String portable = survey.portable;

        for(Computer computer : computers){
            if(!portable.isEmpty() && !String.valueOf(computer.isPortable()).equals(portable.toLowerCase()))
                continue;
            if(brands.contains(computer.getBrand().toLowerCase()))
                comparedComputers.add(computer);
        }
        return comparedComputers;
    }

    /**
     * Compare a set of laptops based on user preferences and
     * return them in an ordered set with the best laptops
     * at the beginning of the set.
     * 
     * @param compareRequest A comparison request containing a survey reponse
     *                       for personalized results and a set of computer ids
     *                       corresponding to the computers to be compared.
     * @return A set of ordered computer objects with the best options first.
     */
    @RequestMapping("/compare")
    public Set<Computer> compareComputers(@RequestBody CompareRequest compareRequest)
    {
        WeightedPreferences weights = new WeightedPreferences(compareRequest.surveyResponse);
        WeightedComparator<Computer> comparator = new WeightedComparator<>(weights);
        Set<Computer> comparedComputers = new TreeSet<>(comparator);
        Iterable<Computer> selected = laptopRepository.findAllById(compareRequest.computerIds);
        for (Computer computer : selected)
        {
            comparedComputers.add(computer);
        }
        return comparedComputers;
    }
}