package com.compucompare.compucompare.computerType;

import com.compucompare.compucompare.components.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Computer
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String brand, model, displayName, thumbnailUrl, pageUrl;
    private boolean portable;

    @ManyToOne
    private CPUComponent processor;

    @ManyToOne
    private GPUComponent graphics;

    @ManyToOne
    @Cascade({CascadeType.SAVE_UPDATE})
    private RAMComponent ram;

    @ManyToMany
    @Cascade({CascadeType.SAVE_UPDATE})
    private Set<StorageComponent> storage;

    @ManyToMany
    @Cascade({CascadeType.SAVE_UPDATE})
    private Set<NetworkComponent> interfaces;

    @ManyToOne
    @Cascade({CascadeType.SAVE_UPDATE})
    private DisplayComponent display;

    @ManyToOne
    @Cascade({CascadeType.SAVE_UPDATE})
    private BatteryComponent battery;

    public Computer()
    {
        brand = "Undefined";
        model = "Undefined";
        displayName = "Undefined";
        thumbnailUrl = "Undefined";
        pageUrl = "No URL Available";
        portable = true;
        processor = null;
        graphics = null;
        ram = null;
        storage = new HashSet<>();
        interfaces = new HashSet<>();
        display = null;
        battery = null;
    }

    public Computer(String brand, String model, String displayName,
                    String thumbnailUrl, String pageUrl, boolean portable,
                    CPUComponent processor, GPUComponent graphics, RAMComponent ram,
                    Set<StorageComponent> storage, Set<NetworkComponent> interfaces,
                    DisplayComponent display, BatteryComponent battery)
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
        this.display = display;
        this.battery = battery;
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

    public boolean isPortable()
    {
        return portable;
    }

    public void setPortable(boolean isPortable)
    {
        portable = isPortable;
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

    public void setDisplay(DisplayComponent display)
    {
        this.display = display;
    }

    public DisplayComponent getDisplay()
    {
        return display;
    }

    public void setBattery(BatteryComponent battery)
    {
        this.battery = battery;
    }

    public BatteryComponent getBattery()
    {
        return battery;
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
            && other.interfaces.equals(interfaces)
            && other.battery.equals(battery)
            && other.display.equals(display);
    }
}