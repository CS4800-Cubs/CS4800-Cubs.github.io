package com.compucompare.compucompare.components;

import java.util.List;
import java.util.ArrayList;

public class NetworkComponent extends Component
{
    private int maxSpeed;
    private boolean wireless;
    private List<String> standards;

    public NetworkComponent(String brand, String model, int maxSpeed,
                            boolean wireless, List<String> standards)
    {
        super(brand, model);
        this.maxSpeed = maxSpeed;
        this.wireless = wireless;
        this.standards = new ArrayList<>(standards);
    }

    public int getMaxSpeed()
    {
        return maxSpeed;
    }

    public boolean isWireless()
    {
        return wireless;
    }

    public List<String> getStandards()
    {
        return new ArrayList<>(standards);
    }
}
