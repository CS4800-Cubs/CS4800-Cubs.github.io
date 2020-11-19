package com.compucompare.compucompare.components;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import com.compucompare.compucompare.comparison.WeightedComparable;
import com.compucompare.compucompare.comparison.WeightedPreferences;

@Entity
public class NetworkComponent extends Component
    implements Comparable<NetworkComponent>, WeightedComparable<NetworkComponent>
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
    public int compareTo(NetworkComponent other)
    {
        return (maxSpeed == 0 || other.maxSpeed == 0)
               ? 0 : maxSpeed - other.maxSpeed;
    }

    @Override
    public int compareTo(NetworkComponent other, WeightedPreferences weights)
    {
        int result = (maxSpeed == 0 || other.maxSpeed == 0)
                      ? 0 : (int) ((wireless ? weights.getWiredSpeedMultiplier() :
                      weights.getWiredSpeedMultiplier()) * (maxSpeed - other.maxSpeed));
        if (result == 0 && !this.equals(other))
        {
            return -1;
        }
        return result;
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
