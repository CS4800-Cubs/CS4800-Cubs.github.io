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
        standards = new HashSet<>();
    }

    public NetworkComponent(String brand, String model, int maxSpeed,
                            boolean wireless, Set<String> standards)
    {
        super(brand, model);
        this.maxSpeed = maxSpeed;
        this.wireless = wireless;
        this.standards = new HashSet<>(standards);
    }

    public void setMaxSpeed(int maxSpeed)
    {
        this.maxSpeed = maxSpeed;
    }

    public int getMaxSpeed()
    {
        return maxSpeed;
    }

    public void setWireless(boolean wireless)
    {
        this.wireless = wireless;
    }

    public boolean isWireless()
    {
        return wireless;
    }

    public void setStandards(Set<String> standards)
    {
        this.standards = new HashSet<>(standards);
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
