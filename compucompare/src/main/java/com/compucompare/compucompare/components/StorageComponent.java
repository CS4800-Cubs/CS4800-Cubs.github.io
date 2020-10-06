package com.compucompare.compucompare.components;

import javax.persistence.Entity;

@Entity
public class StorageComponent extends Component{

    private double capacity;
    private boolean solidState;
    private boolean nvme;

    public StorageComponent()
    {
        capacity = 0.0;
        solidState = false;
        nvme = false;
    }

    public StorageComponent(String brand, String model, double capacity,
                            boolean solidState, boolean nvme){
        super(brand, model);
        this.capacity = capacity;
        this.solidState = solidState;
        this.nvme = nvme;
    }

    public double getCapacity(){return capacity;}

    public boolean getSolidState(){return solidState;}

    public boolean getNvme(){return nvme;}

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof StorageComponent))
        {
            return false;
        }
        StorageComponent other = (StorageComponent) o;
        return super.equals(o)
            && other.capacity == capacity
            && other.solidState == solidState
            && other.nvme == nvme;
    }
}
