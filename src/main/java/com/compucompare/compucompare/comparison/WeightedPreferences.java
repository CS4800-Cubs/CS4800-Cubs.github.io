package com.compucompare.compucompare.comparison;

/**
 * Used for customized comparisons between computer
 * objects, where static standard multipliers
 * scale the varying component scores to their general
 * importance in a system and customized weights adjust
 * the importance of certain aspects for a certain use case.
 */
public class WeightedPreferences
{
    // Standard CPU Weight Multipliers
    public static double STANDARD_SINGLE_THREAD_MULTIPLIER = 1.0;
    public static double STANDARD_MULTI_THREAD_MULTIPLIER = 1.0;
    public static double STANDARD_NUMBER_CORES_MULTIPLIER = 1.0;

    // Standard RAM Weight Multipliers
    public static double STANDARD_RAM_AMOUNT_MULTIPLIER = 1.0;
    public static double STANDARD_RAM_SPEED_MULTIPLIER = 1.0;
    public static double STANDARD_DUAL_CHANNEL_MULTIPLIER = 0.0;

    // Standard GPU Weight Multipliers
    public static double STANDARD_GPU_MULTIPLIER = 1.0;

    // Standard Battery Weight Multipliers
    public static double STANDARD_BATTERY_LIFE_MULTIPLIER = 1.0;
    public static double STANDARD_BATTERY_CAPACITY_MULTIPLIER = 1.0;

    // Standard Display Weight Multipliers
    public static double STANDARD_REFRESH_RATE_MULTIPLIER = 1.0;
    public static double STANDARD_SCREEN_SIZE_MULTIPLIER = 1.0;
    public static double STANDARD_SCREEN_RESOLUTION_WEIGHT = 1.0;

    // Standard Network Weight Multipliers
    public static double STANDARD_WIRELESS_SPEED_MULTIPLIER = 1.0;
    public static double STANDARD_WIRED_SPEED_MULTIPLIER = 1.0;

    // Standard Storage Weight Multipliers
    public static double STANDARD_STORAGE_CAPACITY_MULTIPLIER = 1.0;
    public static double STANDARD_STORAGE_SSD_MULTIPLIER = 1.0;
    public static double STANDARD_STORAGE_NVME_MULTIPLIER = 1.0;

    // CPU Weights
    public double singleThreadWeight;
    public double multiThreadWeight;
    public double numCoresWeight;

    // RAM Weights
    public double ramAmountWeight;
    public double ramSpeedWeight;
    public double dualChannelWeight;

    // GPU Weights
    public double gpuScoreWeight;

    // Battery Weights
    public double batteryLifeWeight;
    public double batteryCapacityWeight;

    // Display Weights
    public double refreshRateWeight;
    public double screenSizeWeight;
    public double screenResolutionWeight;

    // Networking Weights
    public double wirelessSpeedWeight;
    public double wiredSpeedWeight;

    // Storage Weights
    public double storageCapacityWeight;
    public double storageSolidStateWeight;
    public double storageNvmeWeight;

    /**
     * Set default weights, all set to a standard 1.0.
     * Adjustments to these values adjust their importance
     * accordingly. Increasing one of these weights to 2.0
     * would make the corresponding aspect twice as important
     * as it generally would be for a general use case.
     */
    public WeightedPreferences()
    {
        singleThreadWeight = 1.0;
        multiThreadWeight = 1.0;
        numCoresWeight = 1.0;
        ramAmountWeight = 1.0;
        ramSpeedWeight = 1.0;
        dualChannelWeight = 0.0;
        gpuScoreWeight = 1.0;
        batteryLifeWeight = 1.0;
        batteryCapacityWeight = 1.0;
        refreshRateWeight = 1.0;
        screenSizeWeight = 1.0;
        screenResolutionWeight = 1.0;
        wirelessSpeedWeight = 1.0;
        wiredSpeedWeight = 1.0;
        storageCapacityWeight = 1.0;
        storageSolidStateWeight = 1.0;
        storageNvmeWeight = 1.0;
    }

    public double getSingleThreadMultiplier()
    {
        return STANDARD_SINGLE_THREAD_MULTIPLIER * singleThreadWeight;
    }

    public double getMultiThreadMultiplier()
    {
        return STANDARD_MULTI_THREAD_MULTIPLIER * multiThreadWeight;
    }

    public double getRamAmountMultiplier()
    {
        return STANDARD_RAM_AMOUNT_MULTIPLIER * ramAmountWeight;
    }

    public double getRamSpeedMultiplier()
    {
        return STANDARD_RAM_SPEED_MULTIPLIER * ramSpeedWeight;
    }

    public double getDualChannelMultiplier()
    {
        return STANDARD_DUAL_CHANNEL_MULTIPLIER * dualChannelWeight;
    }

    public double getGraphicsMultiplier()
    {
        return STANDARD_GPU_MULTIPLIER * gpuScoreWeight;
    }

    public double getBatteryLifeMultiplier()
    {
        return STANDARD_BATTERY_LIFE_MULTIPLIER * batteryLifeWeight;
    }

    public double getBatteryCapacityMultiplier()
    {
        return STANDARD_BATTERY_CAPACITY_MULTIPLIER * batteryCapacityWeight;
    }

    public double getScreenSizeMultiplier()
    {
        return STANDARD_SCREEN_SIZE_MULTIPLIER * screenSizeWeight;
    }

    public double getScreenResolutionMultiplier()
    {
        return STANDARD_SCREEN_RESOLUTION_WEIGHT * screenResolutionWeight;
    }

    public double getWirelessSpeedMultiplier()
    {
        return STANDARD_WIRELESS_SPEED_MULTIPLIER * wirelessSpeedWeight;
    }

    public double getWiredSpeedMultiplier()
    {
        return STANDARD_WIRED_SPEED_MULTIPLIER * wiredSpeedWeight;
    }

    public double getStorageCapacityMultiplier()
    {
        return STANDARD_STORAGE_CAPACITY_MULTIPLIER * storageCapacityWeight;
    }

    public double getSolidStateMultiplier()
    {
        return STANDARD_STORAGE_SSD_MULTIPLIER * storageSolidStateWeight;
    }

    public double getNvmeMultiplier()
    {
        return STANDARD_STORAGE_NVME_MULTIPLIER * storageNvmeWeight;
    }
}
