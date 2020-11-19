package com.compucompare.compucompare.comparison;

import java.util.Comparator;

public class WeightedComparator<T extends WeightedComparable<T>> implements Comparator<T>
{
    private WeightedPreferences weights;

    public WeightedComparator(WeightedPreferences weights)
    {
        this.weights = weights;
    }

    @Override
    public int compare(T first, T second)
    {
        return -first.compareTo(second, weights);
    }
}