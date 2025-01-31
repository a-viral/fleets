package org.example.model;

public class Charger {
    private long id;
    private int chargingRate;

    public Charger(long id, int chargingRate) {
        this.id = id;
        this.chargingRate = chargingRate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getChargingRate() {
        return chargingRate;
    }

    public void setChargingRate(int chargingRate) {
        this.chargingRate = chargingRate;
    }

    @Override
    public String toString() {
        return "Charger{" +
               "id=" + id +
               '}';
    }
}
