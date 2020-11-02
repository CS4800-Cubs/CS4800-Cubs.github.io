package com.compucompare.compucompare.components;

import javax.persistence.Entity;

@Entity
public class CPUComponent extends Component
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
