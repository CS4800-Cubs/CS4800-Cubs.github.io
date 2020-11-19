package com.compucompare.compucompare.components;

import javax.persistence.Entity;

import com.compucompare.compucompare.comparison.WeightedComparable;
import com.compucompare.compucompare.comparison.WeightedPreferences;

@Entity
public class BatteryComponent extends Component
    implements Comparable<BatteryComponent>, WeightedComparable<BatteryComponent>
{

    private double expectedLife;
    private int capacity;

    public BatteryComponent()
    {
        super();
        expectedLife = 0.0;
        capacity = 0;
    }

    public BatteryComponent(String brand, String model,
                            double expectedLife, int capacity)
    {
        super(brand, model);
        this.expectedLife = expectedLife;
        this.capacity = capacity;
    }

    public double getExpectedLife()
    {
        return expectedLife;
    }

    public int getCapacity()
    {
        return capacity;
    }

    @Override
    public int compareTo(BatteryComponent other)
    {
        int result = expectedLife == 0.0 || other.expectedLife == 0.0
                     ? 0 : (int) (expectedLife - other.expectedLife);
        result += capacity == 0 || other.capacity == 0
                  ? 0 : capacity - other.capacity;
        return result;
    }

    @Override
    public int compareTo(BatteryComponent other, WeightedPreferences weights)
    {
        int result = expectedLife == 0.0 || other.expectedLife == 0.0
                     ? 0 : (int) (weights.getBatteryLifeMultiplier() * (expectedLife - other.expectedLife));
        result += capacity == 0 || other.capacity == 0
                  ? 0 : (int) (weights.getBatteryCapacityMultiplier() * (capacity - other.capacity));
        if (result == 0 && !this.equals(other))
        {
            return -1;
        }
        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof BatteryComponent))
        {
            return false;
        }
        BatteryComponent other = (BatteryComponent) o;
        return super.equals(o)
            && other.expectedLife == expectedLife
            && other.capacity == capacity;
    }
}