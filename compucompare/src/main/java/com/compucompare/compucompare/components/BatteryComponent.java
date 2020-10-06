package com.compucompare.compucompare.components;

import javax.persistence.Entity;

@Entity
public class BatteryComponent extends Component
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

}