package org.example.model;

public abstract class Entity {
    
    public String address;
    public float area;

    public Entity(String address, float area){
        this.address = address;
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public float getArea() {
        return area;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setArea(float area) {
        this.area = area;
    }
}
