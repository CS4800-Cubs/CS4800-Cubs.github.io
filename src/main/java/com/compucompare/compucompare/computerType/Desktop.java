package com.compucompare.compucompare.computerType;

import java.util.Set;

import javax.persistence.Entity;

import com.compucompare.compucompare.components.CPUComponent;
import com.compucompare.compucompare.components.GPUComponent;
import com.compucompare.compucompare.components.NetworkComponent;
import com.compucompare.compucompare.components.RAMComponent;
import com.compucompare.compucompare.components.StorageComponent;

@Entity
public class Desktop extends Computer
{
    public Desktop()
    {
        super();
    }

    public Desktop(String brand, String model, CPUComponent processor,
                   GPUComponent graphics, RAMComponent ram,
                   Set<StorageComponent> storage, Set<NetworkComponent> interfaces)
    {
        super(brand, model, processor, graphics, ram, storage, interfaces);
    }
}