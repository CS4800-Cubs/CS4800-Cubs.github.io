package com.compucompare.compucompare.components;

public class RAMComponent extends Component{
    private double memory;
    private int speed;
    private boolean dualChannel;

    public RAMComponent(String brand, String model, double memory,
                        int speed, boolean dualChannel){
        super(brand,model);
        this.memory = memory;
        this.speed = speed;
        this.dualChannel = dualChannel;
    }

    public double getMemory(){return memory;}

    public int getSpeed(){return speed;}

    public boolean getDualChannel(){return dualChannel;}
}
