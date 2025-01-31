package org.example.model;

public class Truck {
    private long id;
    private int batteryCapacity;
    private int remainingBattery;
    private int batteryToCharge;

    public Truck(long id, int batteryCapacity, int remainingBattery) {
        this.id = id;
        this.batteryCapacity = batteryCapacity;
        this.remainingBattery = remainingBattery;
        this.batteryToCharge = batteryCapacity - remainingBattery;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public int getRemainingBattery() {
        return remainingBattery;
    }

    public void setRemainingBattery(int remainingBattery) {
        this.remainingBattery = remainingBattery;
    }

    public int getBatteryToCharge() {
        return batteryToCharge;
    }

    @Override
    public String toString() {
        return "Truck{" +
               "id=" + id +
               '}';
    }
}
