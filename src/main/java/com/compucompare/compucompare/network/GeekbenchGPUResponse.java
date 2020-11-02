package com.compucompare.compucompare.network;

public class GeekbenchGPUResponse
{
    private GPUBenchmark[] devices;

    public void setDevices(GPUBenchmark[] devices)
    {
        this.devices = devices;
    }

    public GPUBenchmark[] getDevices()
    {
        return devices;
    }
}