package com.compucompare.compucompare.components;

import javax.persistence.Entity;

@Entity
public class GPUComponent extends Component
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
}