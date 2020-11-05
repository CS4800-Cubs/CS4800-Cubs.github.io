package com.compucompare.compucompare.routines;

import com.compucompare.compucompare.components.CPUComponent;
import com.compucompare.compucompare.components.GPUComponent;
import com.compucompare.compucompare.database.CPURepository;
import com.compucompare.compucompare.database.GPURepository;
import com.compucompare.compucompare.network.CPUBenchmark;
import com.compucompare.compucompare.network.GPUBenchmark;
import com.compucompare.compucompare.network.GeekbenchCPUResponse;
import com.compucompare.compucompare.network.GeekbenchGPUResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DatabaseUpdateTask
{
    public static final String CPU_BENCHMARK_URL = "https://browser.geekbench.com/processor-benchmarks.json";
    public static final String GPU_BENCHMARK_URL = "https://browser.geekbench.com/vulkan-benchmarks.json";

    @Autowired
    CPURepository cpuRepository;

    @Autowired
    GPURepository gpuRepository;

    @Scheduled(fixedDelay = 86400000, initialDelay = 0)
    public void updateDatabase()
    {
        RestTemplate restTemplate = new RestTemplate();

        // Update CPU Database
        GeekbenchCPUResponse cpuResponse = restTemplate.getForObject(CPU_BENCHMARK_URL, GeekbenchCPUResponse.class);
        CPUBenchmark[] cpuBenchmarks = cpuResponse.getDevices();
        for (CPUBenchmark benchmark : cpuBenchmarks)
        {
            String[] name = benchmark.getName().split(" ", 2);
            String[] description = benchmark.getDescription().split("(\\s|\\(|\\))+", 4);
            if (name.length == 2 && description.length == 4)
            {
                String brand = name[0];
                String model = name[1];
                CPUComponent cpuComponent = cpuRepository.findByBrandAndModel(brand, model);
                if (cpuComponent == null)
                {
                    cpuComponent = new CPUComponent();
                    cpuComponent.setBrand(brand);
                    cpuComponent.setModel(model);
                }
                int numCores = Integer.parseInt(description[2]);
                cpuComponent.setSingleBench(benchmark.getSingleBench());
                cpuComponent.setMultiBench(benchmark.getMultiBench());
                cpuComponent.setNumCores(numCores);
                cpuRepository.save(cpuComponent);
            }
            else
            {
                System.out.println("Unexpected CPU formatting received from Geekbench!");
            }
        }

        // Update GPU Database
        GeekbenchGPUResponse gpuResponse = restTemplate.getForObject(GPU_BENCHMARK_URL, GeekbenchGPUResponse.class);
        GPUBenchmark[] gpuBenchmarks = gpuResponse.getDevices();
        for (GPUBenchmark benchmark : gpuBenchmarks)
        {
            String[] name = benchmark.getName().split("(\\s|-|\\(TM\\))+", 2);
            if (name.length == 2)
            {
                String brand = name[0];
                String model = name[1];
                GPUComponent gpuComponent = gpuRepository.findByBrandAndModel(brand, model);
                if (gpuComponent == null)
                {
                    gpuComponent = new GPUComponent();
                    gpuComponent.setBrand(brand);
                    gpuComponent.setModel(model);
                }
                gpuComponent.setBenchmark(benchmark.getScore());
                gpuRepository.save(gpuComponent);
            }
            else
            {
                System.out.println("Unexpected GPU formatting received from Geekbench!");
            }
        }
    }
}
