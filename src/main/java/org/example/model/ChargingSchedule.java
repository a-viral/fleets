package org.example.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChargingSchedule {
    private Map<Charger, List<Truck>> chargerTruckMap;

    public ChargingSchedule() {
        this.chargerTruckMap = new HashMap<>();
    }

    public void addTruck(Charger charger, Truck truck) {
        List<Truck> trucks = chargerTruckMap.getOrDefault(charger, new ArrayList<>());
        trucks.add(truck);
        chargerTruckMap.put(charger, trucks);
    }

    public Map<Charger, List<Truck>> getChargerTruckMap() {
        return chargerTruckMap;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Charger, List<Truck>> entry : chargerTruckMap.entrySet()) {
            result.append(entry.getKey().getId())
                    .append(": ");
            List<Truck> trucks = entry.getValue();
            for (int i = 0; i < trucks.size(); i++) {
                result.append(trucks.get(i).getId());
                if (i < trucks.size() - 1) {
                    result.append(", ");
                }
            }
            result.append("\n");
        }
        return result.toString().trim();
    }
}
