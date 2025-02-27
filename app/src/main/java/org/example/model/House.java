package org.example.model;

public class House extends Entity {
    private int floors;
    private boolean garage;

    public House(String address, double area, int floors, boolean garage) {
        super(address, area);
        this.floors = floors;
        this.garage = garage;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public boolean hasGarage() {
        return garage;
    }

    public void setGarage(boolean garage) {
        this.garage = garage;
    }

    @Override
    public String toString() {
        return super.toString() + ", Floors: " + floors + ", Garage: " + garage;
    }
}
