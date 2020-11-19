package com.compucompare.compucompare.comparison;

import com.compucompare.compucompare.SurveyResponse;

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
    public static double standardSingleThreadMultiplier = 2.0;
    public static double standardMultiThreadMultiplier = 2.0;
    public static double standardNumberCoresMultiplier = 2.0;

    // Standard RAM Weight Multipliers
    public static double standardRamAmountMultiplier = 1.0;
    public static double standardRamSpeedMultiplier = 1.0;
    public static double standardDualChannelMultiplier = 0.0;

    // Standard GPU Weight Multipliers
    public static double standardGpuMultiplier = 0.5;

    // Standard Battery Weight Multipliers
    public static double standardBatteryLifeMultiplier = 1.0;
    public static double standardBatteryCapacityMultiplier = 1.0;

    // Standard Display Weight Multipliers
    public static double standardRefreshRateMultiplier = 1.0;
    public static double standardScreenSizeMultiplier = 1.0;
    public static double standardScreenResolutionMultiplier = 1.0;

    // Standard Network Weight Multipliers
    public static double standardWirelessSpeedMultiplier = 1.0;
    public static double standardWiredSpeedMultiplier = 1.0;

    // Standard Storage Weight Multipliers
    public static double standardStorageCapacityMultiplier = 1.0;
    public static double standardSolidStateMultiplier = 1.0;
    public static double standardNvmeMultiplier = 1.0;

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

    /**
     * Assign weights based on the user's
     * survey responses.
     * 
     * @param survey
     */
    public WeightedPreferences(SurveyResponse survey)
    {
        this();
        for (String category : survey.categories)
        {
            if (category.equalsIgnoreCase("editing"))
            {
                singleThreadWeight += 1.0;
                multiThreadWeight += 4.0;
                numCoresWeight += 4.0;
                ramAmountWeight += 2.0;
                ramSpeedWeight += 0.5;
                gpuScoreWeight += 1.0;
                storageCapacityWeight += 2.0;
            }
            else if (category.equalsIgnoreCase("gaming"))
            {
                singleThreadWeight += 1.5;
                multiThreadWeight += 0.5;
                ramAmountWeight += 0.5;
                ramSpeedWeight += 0.5;
                gpuScoreWeight += 2.5;
                storageCapacityWeight += 0.5;
                storageSolidStateWeight += 0.5;
                storageNvmeWeight += 0.25;
            }
            else if (category.equalsIgnoreCase("education"))
            {
                batteryLifeWeight += 1.0;
                batteryCapacityWeight += 1.0;
                singleThreadWeight += 0.5;
                ramAmountWeight += 0.5;
                storageCapacityWeight += 1.0;
                storageSolidStateWeight += 0.75;
                storageNvmeWeight += 0.25;
            }
            else if (category.equalsIgnoreCase("work"))
            {
                batteryLifeWeight += 1.0;
                batteryCapacityWeight += 1.0;
                singleThreadWeight += 1.0;
                multiThreadWeight += 0.5;
                ramAmountWeight += 0.5;
                storageCapacityWeight += 1.0;
                storageSolidStateWeight += 0.75;
                storageNvmeWeight += 0.25;
            }
        }
        // Don't Give Huge Advantages To Laptops Compared
        // To Desktops Based On Battery Life
        if (survey.portable.isEmpty())
        {
            batteryCapacityWeight = 0.5;
            batteryLifeWeight = 0.5;
        }
        else if (survey.portable.equalsIgnoreCase("false"))
        {
            batteryCapacityWeight = 0.0;
            batteryLifeWeight = 0.0;
        }
    }

    public double getSingleThreadMultiplier()
    {
        return standardSingleThreadMultiplier * singleThreadWeight;
    }

    public double getMultiThreadMultiplier()
    {
        return standardMultiThreadMultiplier * multiThreadWeight;
    }

    public double getRamAmountMultiplier()
    {
        return standardRamAmountMultiplier * ramAmountWeight;
    }

    public double getRamSpeedMultiplier()
    {
        return standardRamSpeedMultiplier * ramSpeedWeight;
    }

    public double getDualChannelMultiplier()
    {
        return standardDualChannelMultiplier * dualChannelWeight;
    }

    public double getGraphicsMultiplier()
    {
        return standardGpuMultiplier * gpuScoreWeight;
    }

    public double getBatteryLifeMultiplier()
    {
        return standardBatteryLifeMultiplier * batteryLifeWeight;
    }

    public double getBatteryCapacityMultiplier()
    {
        return standardBatteryCapacityMultiplier * batteryCapacityWeight;
    }

    public double getScreenSizeMultiplier()
    {
        return standardScreenSizeMultiplier * screenSizeWeight;
    }

    public double getScreenResolutionMultiplier()
    {
        return standardScreenResolutionMultiplier * screenResolutionWeight;
    }

    public double getWirelessSpeedMultiplier()
    {
        return standardWirelessSpeedMultiplier * wirelessSpeedWeight;
    }

    public double getWiredSpeedMultiplier()
    {
        return standardWiredSpeedMultiplier * wiredSpeedWeight;
    }

    public double getStorageCapacityMultiplier()
    {
        return standardStorageCapacityMultiplier * storageCapacityWeight;
    }

    public double getSolidStateMultiplier()
    {
        return standardSolidStateMultiplier * storageSolidStateWeight;
    }

    public double getNvmeMultiplier()
    {
        return standardNvmeMultiplier * storageNvmeWeight;
    }
}
