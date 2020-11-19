package com.compucompare.compucompare.computerType;

import com.compucompare.compucompare.comparison.WeightedComparable;
import com.compucompare.compucompare.comparison.WeightedPreferences;
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
public class Computer implements Comparable<Computer>, WeightedComparable<Computer>
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
    public int compareTo(Computer other)
    {
        int result = processor.compareTo(other.processor)
            + graphics.compareTo(other.graphics)
            + ram.compareTo(other.ram)
            + battery.compareTo(other.battery)
            + display.compareTo(other.display);
        result += compareDrives(other, null);
        result += compareInterfaces(other, null);
        return result;
    }

    @Override
    public int compareTo(Computer other, WeightedPreferences weights)
    {
        int result = processor.compareTo(other.processor, weights)
            + graphics.compareTo(other.graphics, weights)
            + ram.compareTo(other.ram, weights)
            + battery.compareTo(other.battery, weights)
            + display.compareTo(other.display, weights);
        result += compareDrives(other, weights);
        result += compareInterfaces(other, weights);
        if (result == 0 && !this.equals(other))
        {
            return -1;
        }
        return result;
    }

    public int compareDrives(Computer other, WeightedPreferences weights)
    {
        int result = 0;
        StorageComponent[] hardDrive = new StorageComponent[2];
        StorageComponent[] solidStateDrive = new StorageComponent[2];
        StorageComponent[] nvmeDrive = new StorageComponent[2];
        for (StorageComponent drive : storage)
        {
            if (drive.isNvme())
            {
                nvmeDrive[0] = drive;
            }
            else if (drive.isSolidState())
            {
                solidStateDrive[0] = drive;
            }
            else
            {
                hardDrive[0] = drive;
            }
        }
        for (StorageComponent drive : other.storage)
        {
            if (drive.isNvme())
            {
                nvmeDrive[1] = drive;
            }
            else if (drive.isSolidState())
            {
                solidStateDrive[1] = drive;
            }
            else
            {
                hardDrive[1] = drive;
            }
        }
        if (hardDrive[0] != null && hardDrive[1] != null)
        {
            if (weights == null)
            {
                result += hardDrive[0].compareTo(hardDrive[1]);
            }
            else
            {
                result += hardDrive[0].compareTo(hardDrive[1], weights);
            }
        }
        if (solidStateDrive[0] != null && solidStateDrive[1] != null)
        {
            if (weights == null)
            {
                result += solidStateDrive[0].compareTo(solidStateDrive[1]);
            }
            else
            {
                result += solidStateDrive[0].compareTo(solidStateDrive[1], weights);
            }
        }
        if (nvmeDrive[0] != null && nvmeDrive[1] != null)
        {
            if (weights == null)
            {
                result += nvmeDrive[0].compareTo(nvmeDrive[1]);
            }
            else
            {
                result += nvmeDrive[0].compareTo(nvmeDrive[1], weights);
            }
        }
        return result;
    }

    public int compareInterfaces(Computer other, WeightedPreferences weights)
    {
        int result = 0;
        NetworkComponent[] wireless = new NetworkComponent[2];
        NetworkComponent[] wired = new NetworkComponent[2];
        for (NetworkComponent adapter : interfaces)
        {
            if (adapter.isWireless())
            {
                wireless[0] = adapter;
            }
            else
            {
                wired[0] = adapter;
            }
        }
        for (NetworkComponent adapter : other.interfaces)
        {
            if (adapter.isWireless())
            {
                wireless[1] = adapter;
            }
            else
            {
                wired[1] = adapter;
            }
        }
        if (wireless[0] != null && wireless[1] != null)
        {
            result += wireless[0].compareTo(wireless[1]);
        }
        if (wired[0] != null && wired[1] != null)
        {
            result += wired[0].compareTo(wired[1]);
        }
        return result;
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