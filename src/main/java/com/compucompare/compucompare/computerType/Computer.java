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

    private String brand, model, displayName, thumbnailUrl, pageUrl;
    
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
        displayName = "Undefined";
        thumbnailUrl = "Undefined";
        pageUrl = "No URL Available";
        processor = null;
        graphics = null;
        ram = null;
        storage = null;
        interfaces = null;
    }

    public Computer(String brand, String model, String displayName,
                    String thumbnailUrl, String pageUrl, CPUComponent processor,
                    GPUComponent graphics, RAMComponent ram,
                    Set<StorageComponent> storage, Set<NetworkComponent> interfaces)
    {
        this.brand = brand;
        this.model = model;
        this.thumbnailUrl = thumbnailUrl;
        this.pageUrl = pageUrl;
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

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getModel() 
    {
        return model;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }
    
    public String getDisplayName()
    {
        return displayName;
    }

    public void setThumnailUrl(String thumbnailUrl)
    {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getThumbnailUrl()
    {
        return thumbnailUrl;
    }

    public void setPageUrl(String pageUrl)
    {
        this.pageUrl = pageUrl;
    }

    public String getPageUrl()
    {
        return pageUrl;
    }

    public void setProcessor(CPUComponent processor)
    {
        this.processor = processor;
    }

    public CPUComponent getProcessor()
    {
        return processor;
    }

    public void setGraphics(GPUComponent graphics)
    {
        this.graphics = graphics;
    }

    public GPUComponent getGraphics()
    {
        return graphics;
    }

    public void setRam(RAMComponent ram)
    {
        this.ram = ram;
    }

    public RAMComponent getRam()
    {
        return ram;
    }

    public void setStorage(Set<StorageComponent> storage)
    {
        this.storage = storage;
    }

    public void addStorage(StorageComponent storageComponent)
    {
        storage.add(storageComponent);
    }

    public Set<StorageComponent> getStorage()
    {
        return storage;
    }

    public void setInterfaces(Set<NetworkComponent> interfaces)
    {
        this.interfaces = interfaces;
    }

    public void addInterface(NetworkComponent networkComponent)
    {
        interfaces.add(networkComponent);
    }

    public Set<NetworkComponent> getInterfaces()
    {
        return interfaces;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Computer))
        {
            return false;
        }
        Computer other = (Computer) o;
        return other.id == id
            && other.brand.equals(brand)
            && other.model.equals(model)
            && other.processor.equals(processor)
            && other.graphics.equals(graphics)
            && other.ram.equals(ram)
            && other.storage.equals(storage)
            && other.interfaces.equals(interfaces);
    }
}
