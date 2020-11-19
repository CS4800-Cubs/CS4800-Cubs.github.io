package com.compucompare.compucompare.components;

import javax.persistence.Entity;

import com.compucompare.compucompare.comparison.WeightedComparable;
import com.compucompare.compucompare.comparison.WeightedPreferences;

@Entity
public class GPUComponent extends Component
    implements Comparable<GPUComponent>, WeightedComparable<GPUComponent>
{
    private int benchmark;

    public GPUComponent()
    {
        benchmark = 0;
    }

    public GPUComponent(String brand, String model, int benchmark)
    {
        super(brand, model);
        this.benchmark = benchmark;
    }

    public void setBenchmark(int benchmark)
    {
        this.benchmark = benchmark;
    }

    public int getBenchmark()
    {
        return this.benchmark;
    }

    @Override
    public int compareTo(GPUComponent other)
    {
        return (benchmark == 0 || other.benchmark == 0)
               ? 0 : benchmark - other.benchmark;
    }

    @Override
    public int compareTo(GPUComponent other, WeightedPreferences weights)
    {
        int result = (benchmark == 0 || other.benchmark == 0)
                      ? 0 : (int) (weights.getGraphicsMultiplier() * (benchmark - other.benchmark));
        if (result == 0 && !this.equals(other))
        {
            return -1;
        }
        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof GPUComponent))
        {
            return false;
        }
        GPUComponent other = (GPUComponent) o;
        return super.equals(o)
            && other.benchmark == benchmark;
    }
}