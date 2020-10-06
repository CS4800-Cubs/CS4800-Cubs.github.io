package com.compucompare.compucompare.components;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Component
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String brand;
    private String model;

    public Component()
    {
        this.brand = "Undefined";
        this.model = "Undefined";
    }

    public Component(String brand, String model)
    {
        this.brand = brand;
        this.model = model;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getBrand()
    {
        return brand;
    }

    public String getModel()
    {
        return model;
    }
}
