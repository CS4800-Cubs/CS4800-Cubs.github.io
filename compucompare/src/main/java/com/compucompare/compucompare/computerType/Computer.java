package com.compucompare.compucompare.computerType;

import com.compucompare.compucompare.components.*;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@MappedSuperclass
public class Computer
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String brand, model;
    
    @OneToOne (cascade = CascadeType.ALL)
    private CPUComponent processor;

    @OneToOne (cascade = CascadeType.ALL)
    private GPUComponent graphics;

    @OneToOne (cascade = CascadeType.ALL)
    private RAMComponent ram;

    @OneToMany (cascade = CascadeType.ALL)
    private Set<StorageComponent> storage;

    @OneToMany (cascade = CascadeType.ALL)
    private Set<NetworkComponent> interfaces;

    public Computer()
    {
        brand = "Undefined";
        model = "Undefined";
        processor = null;
        graphics = null;
        ram = null;
        storage = null;
        interfaces = null;
    }

    public Computer(String brand, String model, CPUComponent processor,
                    GPUComponent graphics, RAMComponent ram,
                    Set<StorageComponent> storage, Set<NetworkComponent> interfaces)
    {
        this.brand = brand;
        this.model = model;
        this.processor = processor;
        this.graphics = graphics;
        this.ram = ram;
        this.storage = new HashSet<>(storage);
        this.interfaces = new HashSet<>(interfaces);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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

    public Set<StorageComponent> getStorage() {
        return storage;
    }

    public Set<NetworkComponent> getInterfaces() {
        return interfaces;
    }
}
