package com.compucompare.compucompare.components;

import javax.persistence.Entity;

@Entity
public class RAMComponent extends Component{
    private double memory;
    private int speed;
    private boolean dualChannel;

    public RAMComponent()
    {
        super();
        memory = 0.0;
        speed = 0;
        dualChannel = false;
    }

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

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof RAMComponent))
        {
            return false;
        }
        RAMComponent other = (RAMComponent) o;
        return super.equals(o)
            && other.memory == memory
            && other.speed == speed
            && other.dualChannel == dualChannel;
    }
}
