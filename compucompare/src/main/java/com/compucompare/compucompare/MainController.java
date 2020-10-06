package com.compucompare.compucompare;

import com.compucompare.compucompare.computerType.Laptop;
import com.compucompare.compucompare.database.LaptopRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}