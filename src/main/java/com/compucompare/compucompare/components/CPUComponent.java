package com.compucompare.compucompare.components;

import javax.persistence.Entity;

import com.compucompare.compucompare.comparison.WeightedComparable;
import com.compucompare.compucompare.comparison.WeightedPreferences;

@Entity
public class CPUComponent extends Component
    implements Comparable<CPUComponent>, WeightedComparable<CPUComponent>
{
    private int singleBench;
    private int multiBench;
    private int numCores;
    private String architecture;

    public CPUComponent()
    {
        super();
        singleBench = 0;
        multiBench = 0;
        numCores = 0;
        architecture = "Undefined";
    }

    public CPUComponent(String brand, String model, int singleBench,
                        int multiBench, int numCores, String architecture)
    {
        super(brand, model);
        this.singleBench = singleBench;
        this.multiBench = multiBench;
        this.numCores = numCores;
        this.architecture = architecture;
    }

    public void setSingleBench(int singleBench)
    {
        this.singleBench = singleBench;
    }

    public int getSingleBench()
    {
        return singleBench;
    }

    public void setMultiBench(int multiBench)
    {
        this.multiBench = multiBench;
    }

    public int getMultiBench()
    {
        return multiBench;
    }

    public void setNumCores(int numCores)
    {
        this.numCores = numCores;
    }

    public int getNumCores()
    {
        return numCores;
    }

    public void setArchitecture(String architecture)
    {
        this.architecture = architecture;
    }

    public String getArchitecture()
    {
        return architecture;
    }

    @Override
    public int compareTo(CPUComponent other)
    {
        int result = (multiBench == 0 || other.multiBench == 0)
                     ? 0 : multiBench - other.multiBench;
        result += (singleBench == 0 || other.singleBench == 0)
                  ? 0 : singleBench - other.singleBench;
        return result;
    }

    @Override
    public int compareTo(CPUComponent other, WeightedPreferences weights)
    {
        int result = (multiBench == 0 || other.multiBench == 0)
                      ? 0 : (int) (weights.getMultiThreadMultiplier() * (multiBench - other.multiBench));
        result += (singleBench == 0 || other.singleBench == 0)
                   ? 0 : (int) (weights.getSingleThreadMultiplier() * (singleBench - other.singleBench));
        if (result == 0 && !this.equals(other))
        {
            return -1;
        }
        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof CPUComponent))
        {
            return false;
        }
        CPUComponent other = (CPUComponent) o;
        return super.equals(o)
            && other.singleBench == singleBench
            && other.multiBench == multiBench
            && other.numCores == numCores
            && other.architecture.equals(architecture);
    }
}
