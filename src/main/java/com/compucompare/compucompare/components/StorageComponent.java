package com.compucompare.compucompare.components;

import javax.persistence.Entity;

import com.compucompare.compucompare.comparison.WeightedComparable;
import com.compucompare.compucompare.comparison.WeightedPreferences;

@Entity
public class StorageComponent extends Component
    implements Comparable<StorageComponent>, WeightedComparable<StorageComponent>
{

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

    public boolean isSolidState(){return solidState;}

    public boolean isNvme(){return nvme;}

    @Override
    public int compareTo(StorageComponent other)
    {
        int result = (capacity == 0.0 || other.capacity == 0.0)
                     ? 0 : (int) (capacity - other.capacity);
        result += (solidState ? 1 : 0) - (other.solidState ? 1 : 0);
        result += (nvme ? 1 : 0) - (other.nvme ? 1 : 0);
        return result;
    }

    @Override
    public int compareTo(StorageComponent other, WeightedPreferences weights)
    {
        int result = (capacity == 0.0 || other.capacity == 0.0)
                     ? 0 : (int) (weights.getStorageCapacityMultiplier() * (capacity - other.capacity));
        result += (int) (weights.getSolidStateMultiplier() * ((solidState ? 1 : 0) - (other.solidState ? 1 : 0)));
        result += (int) (weights.getNvmeMultiplier() * ((nvme ? 1 : 0) - (other.nvme ? 1 : 0)));
        return result;
    }

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
