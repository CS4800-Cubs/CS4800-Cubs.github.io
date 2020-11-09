package com.compucompare.compucompare.network;

import com.compucompare.compucompare.components.GPUComponent;
import com.compucompare.compucompare.database.GPURepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GeekbenchGPUSource implements DataSource
{
    public static final String GPU_BENCHMARK_URL = "https://browser.geekbench.com/vulkan-benchmarks.json";

    @Autowired
    GPURepository gpuRepository;

    /**
     * Fetches GPU benchmarks from the external source and
     * uses the results to update the database.
     */
    @Override
    public void updateDatabase()
    {
        GPUBenchmark[] benchmarks = fetchGPUs();
        for (GPUBenchmark benchmark : benchmarks)
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

    private GPUBenchmark[] fetchGPUs()
    {
        RestTemplate restTemplate = new RestTemplate();
        GeekbenchGPUResponse gpuResponse = restTemplate.getForObject(GPU_BENCHMARK_URL, GeekbenchGPUResponse.class);
        return gpuResponse.getDevices();
    }
}