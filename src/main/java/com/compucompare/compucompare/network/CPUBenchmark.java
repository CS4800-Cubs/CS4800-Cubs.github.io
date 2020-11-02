package com.compucompare.compucompare.network;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CPUBenchmark
{
    private String name;
    private String description;

    @JsonProperty("score")
    private int singleBench;

    @JsonProperty("multicore_score")
    private int multiBench;

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
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
}
