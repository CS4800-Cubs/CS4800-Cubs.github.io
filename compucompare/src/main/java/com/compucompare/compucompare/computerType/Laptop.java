package com.compucompare.compucompare.computerType;

import com.compucompare.compucompare.components.BatteryComponent;
import com.compucompare.compucompare.components.DisplayComponent;
import com.compucompare.compucompare.components.RAMComponent;
import com.compucompare.compucompare.components.StorageComponent;

import java.util.List;

public class Laptop extends Computer{

    private DisplayComponent display;
    private BatteryComponent battery;

    public Laptop(String brand, String model, CPUComponent processor,
                  GPUComponent graphics, RAMComponent ram,
                  List<StorageComponent> storage, List<NetworkComponent> interfaces,
                  DisplayComponent display, BatteryComponent battery){
        super(brand, model, processor, graphics, ram, storage, interfaces);
        this.display = display;
        this.battery = battery;
    }
}
