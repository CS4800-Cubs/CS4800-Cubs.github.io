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

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof NetworkComponent))
        {
            return false;
        }
        NetworkComponent other = (NetworkComponent) o;
        return super.equals(o)
            && other.maxSpeed == maxSpeed
            && other.wireless == wireless
            && other.standards.equals(standards);
    }
}
