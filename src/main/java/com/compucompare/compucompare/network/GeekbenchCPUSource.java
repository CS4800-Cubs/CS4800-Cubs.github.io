package com.compucompare.compucompare.network;

import com.compucompare.compucompare.components.CPUComponent;
import com.compucompare.compucompare.database.CPURepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GeekbenchCPUSource implements DataSource
{
    public static final String CPU_BENCHMARK_URL = "https://browser.geekbench.com/processor-benchmarks.json";

    @Autowired
    CPURepository cpuRepository;

    /**
     * Fetches CPU benchmarks from the external source and
     * uses the results to update the database.
     */
    @Override
    public void updateDatabase()
    {
        CPUBenchmark[] benchmarks = fetchCPUs();
        for (CPUBenchmark benchmark : benchmarks)
        {
            String[] name = benchmark.getName().split(" ", 2);
            String[] description = benchmark.getDescription().split("(\\s|\\(|\\))+", 4);
            if (name.length == 2 && description.length == 4)
            {
                String brand = name[0].trim();
                String model = name[1].trim();
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
    }

    private CPUBenchmark[] fetchCPUs()
    {
        RestTemplate restTemplate = new RestTemplate();
        GeekbenchCPUResponse cpuResponse = restTemplate.getForObject(CPU_BENCHMARK_URL, GeekbenchCPUResponse.class);
        return cpuResponse.getDevices();
    }
}