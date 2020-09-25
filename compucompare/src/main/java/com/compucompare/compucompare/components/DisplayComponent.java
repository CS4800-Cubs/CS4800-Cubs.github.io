package com.compucompare.compucompare.components;

public class DisplayComponent extends Component{

    private double size;
    private int regX;
    private int regY;
    private int refreshRate;

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
}