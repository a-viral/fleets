package org.example.service.chargingstrategy;

import org.example.model.Charger;
import org.example.model.ChargingSchedule;
import org.example.model.Truck;

import java.util.*;

public class ChargeScheduleGreedy implements ChargeSchedule {
    private Queue<Truck> truckQueue = new PriorityQueue<>(Comparator.comparingInt(Truck::getBatteryToCharge));
    private Queue<Charger> fastestChargers =
            new PriorityQueue<>((o1, o2) -> o2.getChargingRate() - o1.getChargingRate());
    private Map<Integer, List<Charger>> availableAt = new HashMap<>();

    @Override
    public ChargingSchedule getChargingSchedule(List<Truck> trucks, List<Charger> chargers, int hours) {
        ChargingSchedule chargingSchedule = new ChargingSchedule();
        queueTrucksAndCharger(trucks, chargers);
        for (int i = 0; i < hours; i++) {
            reclaimAvailableCharger(i);
            if (truckQueue.isEmpty()) break;
            while (true) {
                if (fastestChargers.isEmpty()) {
                    break;
                }
                Truck truckToSchedule = truckQueue.peek();
                Charger fastestCharger = fastestChargers.peek();
                if (truckToSchedule == null || !isEnoughTimeRemaining(hours, truckToSchedule, fastestCharger, i)) break;
                if (isEnoughTimeRemaining(hours, truckToSchedule, fastestCharger, i)) {
                    truckQueue.poll();
                    fastestChargers.poll();
                    chargingSchedule.addTruck(fastestCharger, truckToSchedule);
                    trackChargerAvailibility(i, truckToSchedule, fastestCharger);
                }
            }
        }
        return chargingSchedule;
    }

    private void queueTrucksAndCharger(List<Truck> trucks, List<Charger> chargers) {
        for (Truck truck : trucks) {
            truckQueue.offer(truck);
        }
        for (Charger charger : chargers) {
            fastestChargers.offer(charger);
        }
    }

    private void trackChargerAvailibility(int i, Truck truckToSchedule, Charger fastestCharger) {
        int chargerAvailableAt = i + (truckToSchedule.getBatteryToCharge() / fastestCharger.getChargingRate());
        List<Charger> chargersAvailable = availableAt.getOrDefault(chargerAvailableAt, new ArrayList<>());
        chargersAvailable.add(fastestCharger);
        availableAt.put(chargerAvailableAt, chargersAvailable);
    }

    private static boolean isEnoughTimeRemaining(int hours, Truck truckToSchedule, Charger fastestCharger, int i) {
        return (truckToSchedule.getBatteryToCharge() / fastestCharger.getChargingRate()) <= (hours - i);
    }

    private void reclaimAvailableCharger(int i) {
        List<Charger> availableChargers = availableAt.getOrDefault(i, new ArrayList<>());
        for (Charger c : availableChargers) {
            fastestChargers.offer(c);
        }
    }
}
