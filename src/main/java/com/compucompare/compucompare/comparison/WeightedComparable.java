package com.compucompare.compucompare.comparison;

public interface WeightedComparable<T>
{
    /**
     * Compares two components using weighted preferences.
     * 
     * @param other The component to compare to this one.
     * @param weights A collection of weighted multipliers.
     * @return An integer representing val(this) - val(other)
     */
    public int compareTo(T other, WeightedPreferences weights);
}