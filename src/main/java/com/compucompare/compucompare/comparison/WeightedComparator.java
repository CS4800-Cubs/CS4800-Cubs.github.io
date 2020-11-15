package com.compucompare.compucompare.comparison;

import java.util.Comparator;

import com.compucompare.compucompare.computerType.Computer;

public class WeightedComparator implements Comparator<Computer>
{
    private WeightedPreferences weights;

    public WeightedComparator(WeightedPreferences weights)
    {
        this.weights = weights;
    }

    @Override
    public int compare(Computer first, Computer second)
    {
        return first.compareTo(second, weights);
    }
}