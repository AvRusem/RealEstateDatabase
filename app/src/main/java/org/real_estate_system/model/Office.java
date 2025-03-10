package org.real_estate_system.model;

public class Office extends Entity {
    private String officeOwner;

    public Office(String address, double area, String officeOwner) {
        super(address, area);
        this.officeOwner = officeOwner;
    }

    public String getOfficeOwner() {
        return officeOwner;
    }

    public void setOfficeOwner(String officeOwner) {
        this.officeOwner = officeOwner;
    }

    @Override
    public String toString() {
        return super.toString() + ", Owner: " + officeOwner;
    }
}
