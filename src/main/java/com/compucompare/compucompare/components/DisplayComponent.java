package com.compucompare.compucompare.components;

import javax.persistence.Entity;

import com.compucompare.compucompare.comparison.WeightedComparable;
import com.compucompare.compucompare.comparison.WeightedPreferences;

@Entity
public class DisplayComponent extends Component
    implements Comparable<DisplayComponent>, WeightedComparable<DisplayComponent>
{

    private double size;
    private int resX;
    private int resY;
    private int refreshRate;

    public DisplayComponent()
    {
        super();
        size = 0.0;
        resX = 0;
        resY = 0;
        refreshRate = 0;
    }

    public DisplayComponent(String brand, String model, double size,
                            int resX, int resY, int refreshRate)
    {
        super(brand,model);
        this.size = size;
        this.resX = resX;
        this.resY = resY;
        this.refreshRate = refreshRate;
    }

    public void setSize(double size)
    {
        this.size = size;
    }

    public double getSize(){return size;}

    public void setRegX(int resX)
    {
        this.resX = resX;
    }

    public int getRegX(){return resX;}

    public void setResX(int resY)
    {
        this.resY = resY;
    }

    public int getRegY(){return resY;}

    public void setRefreshRate(int refreshRate)
    {
        this.refreshRate = refreshRate;
    }

    public int getRefreshRate(){return refreshRate;}

    @Override
    public int compareTo(DisplayComponent other)
    {
        int result = (size == 0.0 || other.size == 0.0) ? 0 : (int) (size - other.size);
        int resolution = resX * resY;
        int otherResolution = other.resX * resY;
        result += (resolution == 0 || otherResolution == 0) ? 0 : resolution - otherResolution;
        return result;
    }

    @Override
    public int compareTo(DisplayComponent other, WeightedPreferences weights)
    {
        int result = (size == 0.0 || other.size == 0.0)
                     ? 0 : (int) (weights.getScreenSizeMultiplier() * (size - other.size));
        int resolution = resX + resY;
        int otherResolution = other.resX + resY;
        result += (resolution == 0 || otherResolution == 0)
                   ? 0 : (int) (weights.getScreenResolutionMultiplier() * (resolution - otherResolution));
        if (result == 0 && !this.equals(other))
        {
            return -1;
        }
        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof DisplayComponent))
        {
            return false;
        }
        DisplayComponent other = (DisplayComponent) o;
        return super.equals(o)
            && other.size == size
            && other.resX == resX
            && other.resY == resY
            && other.refreshRate == refreshRate;
    }
}
