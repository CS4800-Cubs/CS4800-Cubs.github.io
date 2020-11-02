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

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getModel()
    {
        return model;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Component))
        {
            return false;
        }
        Component other = (Component) o;
        return other.id == id
            && other.brand.equals(brand)
            && other.model.equals(model);
    }
}
