package com.compucompare.compucompare.components;

public class StorageComponent extends Component{

    private double capacity;
    private boolean solidState;
    private boolean nvme;

    public StorageComponent(String brand, String model, double capacity,
                            boolean solidState, boolean nvme){
        super(brand, model);
        this.capacity = capacity;
        this.solidState = solidState;
        this.nvme = nvme;
    }

    public double getCapacity(){return capacity;}

    public boolean getSolidState(){return solidState;}

    public boolean getNvme(){return nvme;}
}
