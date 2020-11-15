package com.compucompare.compucompare.components;

import javax.persistence.Entity;

import com.compucompare.compucompare.comparison.WeightedComparable;
import com.compucompare.compucompare.comparison.WeightedPreferences;

@Entity
public class RAMComponent extends Component
    implements Comparable<RAMComponent>, WeightedComparable<RAMComponent>
{
    private double memory;
    private int speed;
    private boolean dualChannel;

    public RAMComponent()
    {
        super();
        memory = 0.0;
        speed = 0;
        dualChannel = false;
    }

    public RAMComponent(String brand, String model, double memory,
                        int speed, boolean dualChannel){
        super(brand,model);
        this.memory = memory;
        this.speed = speed;
        this.dualChannel = dualChannel;
    }

    public double getMemory(){return memory;}

    public int getSpeed(){return speed;}

    public boolean getDualChannel(){return dualChannel;}

    @Override
    public int compareTo(RAMComponent other)
    {
        int result = (memory == 0.0 || other.memory == 0.0)
                     ? 0 : (int) (memory - other.memory);
        result += (speed == 0 || other.speed == 0)
                  ? 0 : speed - other.speed;
        result += (dualChannel ? 1 : 0) - (other.dualChannel ? 1 : 0);
        return result;
    }

    @Override
    public int compareTo(RAMComponent other, WeightedPreferences weights)
    {
        int result = (memory == 0.0 || other.memory == 0.0)
                     ? 0 : (int) (weights.getRamAmountMultiplier() * (memory - other.memory));
        result += (speed == 0 || other.speed == 0)
                  ? 0 : (int) (weights.getRamSpeedMultiplier() * (speed - other.speed));
        result += (int) (weights.getDualChannelMultiplier() * ((dualChannel ? 1 : 0) - (other.dualChannel ? 1 : 0)));
        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof RAMComponent))
        {
            return false;
        }
        RAMComponent other = (RAMComponent) o;
        return super.equals(o)
            && other.memory == memory
            && other.speed == speed
            && other.dualChannel == dualChannel;
    }
}
