package com.compucompare.compucompare.network;

public class GeekbenchCPUResponse
{
    private CPUBenchmark[] devices;

    public void setDevices(CPUBenchmark[] devices)
    {
        this.devices = devices;
    }

    public CPUBenchmark[] getDevices()
    {
        return devices;
    }
}