package com.compucompare.compucompare.computerType;

import com.compucompare.compucompare.components.*;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Laptop extends Computer
{
    @OneToOne (cascade = CascadeType.ALL)
    private DisplayComponent display;

    @OneToOne (cascade = CascadeType.ALL)
    private BatteryComponent battery;

    public Laptop()
    {
        super();
        display = null;
        battery = null;
    }

    public Laptop(String brand, String model, String displayName,
                  String thumbnailUrl, String pageUrl, CPUComponent processor,
                  GPUComponent graphics, RAMComponent ram,
                  Set<StorageComponent> storage, Set<NetworkComponent> interfaces,
                  DisplayComponent display, BatteryComponent battery)
    {
        super(brand, model, displayName, thumbnailUrl, pageUrl,
              processor, graphics, ram, storage, interfaces);
        this.display = display;
        this.battery = battery;
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
        if (!(o instanceof Laptop))
        {
            return false;
        }
        Laptop other = (Laptop) o;
        return super.equals(o)
            && other.battery.equals(battery)
            && other.display.equals(display);
    }
}