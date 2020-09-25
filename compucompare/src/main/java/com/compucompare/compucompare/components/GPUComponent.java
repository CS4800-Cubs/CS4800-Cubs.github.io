package com.compucompare.compucompare.components;

public class GPUComponent extends Component
{
    private int benchmark;

    public GPUComponent(String brand, String model, int benchmark)
    {
        super(brand, model);
        this.benchmark = benchmark;
    }

    public int getBenchmark()
    {
        return this.benchmark;
    }
}