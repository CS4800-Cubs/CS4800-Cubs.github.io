package com.compucompare.compucompare.computerType;

import com.compucompare.compucompare.components.RAMComponent;
import com.compucompare.compucompare.components.StorageComponent;

import java.util.List;

public class Computer {

    private String brand, model;
    private CPUComponent processor;
    private GPUComponent graphics;
    private RAMComponent ram;
    private List<StorageComponent> storage;
    private List<NetworkComponent> interfaces;

    public Computer(String brand, String model, CPUComponent processor,
                    GPUComponent graphics, RAMComponent ram,
                    List<StorageComponent> storage, List<NetworkComponent> interfaces){

        this.brand = brand;
        this.model = model;
        this.processor = processor;
        this.graphics = graphics;
        this.ram = ram;
        this.storage = storage;
        this.interfaces = interfaces;

    }


    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public CPUComponent getProcessor() {
        return processor;
    }

    public GPUComponent getGraphics() {
        return graphics;
    }

    public RAMComponent getRam() {
        return ram;
    }

    public List<StorageComponent> getStorage() {
        return storage;
    }

    public List<NetworkComponent> getInterfaces() {
        return interfaces;
    }
}
