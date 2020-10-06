package com.compucompare.compucompare.components;

import javax.persistence.Entity;

@Entity
public class DisplayComponent extends Component{

    private double size;
    private int regX;
    private int regY;
    private int refreshRate;

    public DisplayComponent()
    {
        super();
        size = 0.0;
        regX = 0;
        regY = 0;
        refreshRate = 0;
    }

    public DisplayComponent(String brand, String model, double size,
                            int regX, int regY, int refreshRate){
        super(brand,model);
        this.size = size;
        this.regX = regX;
        this.regY = regY;
        this.refreshRate = refreshRate;
    }

    public double getSize(){return size;}

    public int getRegX(){return regX;}

    public int getRegY(){return regY;}

    public int getRefreshRate(){return refreshRate;}

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
            && other.regX == regX
            && other.regY == regY
            && other.refreshRate == refreshRate;
    }
}
