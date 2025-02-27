package org.real_estate_system.model;

public class FlatRoom extends Entity {
    private int floor;
    private int flatNumber;

    public FlatRoom(String address, double area, int floor, int flatNumber) {
        super(address, area);
        this.floor = floor;
        this.flatNumber = flatNumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(int flatNumber) {
        this.flatNumber = flatNumber;
    }

    @Override
    public String toString() {
        return super.toString() + ", Floor: " + floor + ", Flat number: " + flatNumber;
    }
}
