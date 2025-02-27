package org.example.model;

public abstract class Entity {
    private String address;
    private double area;

    public Entity(String address, double area) {
        this.address = address;
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Address: " + address + ", Area: " + area;
    }
}
