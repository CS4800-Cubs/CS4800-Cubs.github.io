package com.compucompare.compucompare.components;

public class CPUComponent extends Component
{

    private int singleBench;
    private int multiBench;
    private int numCores;
    private String architecture;

    public CPUComponent(String brand, String model, int singleBench,
                        int multiBench, int numCores, String architecture)
    {
        super(brand, model);
        this.singleBench = singleBench;
        this.multiBench = multiBench;
        this.numCores = numCores;
        this.architecture = architecture;
    }

    public int getSingleBench()
    {
        return singleBench;
    }

    public int getMultiBench()
    {
        return multiBench;
    }

    public int numCores()
    {
        return numCores;
    }

    public String getArchitecture()
    {
        return architecture;
    }
}
