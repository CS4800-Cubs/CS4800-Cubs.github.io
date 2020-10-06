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

    public Laptop(String brand, String model, CPUComponent processor,
                  GPUComponent graphics, RAMComponent ram,
                  Set<StorageComponent> storage, Set<NetworkComponent> interfaces,
                  DisplayComponent display, BatteryComponent battery)
    {
        super(brand, model, processor, graphics, ram, storage, interfaces);
        this.display = display;
        this.battery = battery;
    }

    public DisplayComponent getDisplay() {
        return display;
    }

    public BatteryComponent getBattery() {
        return battery;
    }
}