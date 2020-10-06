package com.compucompare.compucompare.components;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

@Entity
public class NetworkComponent extends Component
{
    private int maxSpeed;
    private boolean wireless;

    @ElementCollection
    private Set<String> standards;

    public NetworkComponent()
    {
        super();
        maxSpeed = 0;
        wireless = false;
    }

    public NetworkComponent(String brand, String model, int maxSpeed,
                            boolean wireless, Set<String> standards)
    {
        super(brand, model);
        this.maxSpeed = maxSpeed;
        this.wireless = wireless;
        this.standards = new HashSet<>(standards);
    }

    public int getMaxSpeed()
    {
        return maxSpeed;
    }

    public boolean isWireless()
    {
        return wireless;
    }

    public Set<String> getStandards()
    {
        return new HashSet<>(standards);
    }
}
