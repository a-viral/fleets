package org.example;

import org.example.model.Charger;
import org.example.model.Truck;
import org.example.service.ChargingService;

import java.util.List;

import static org.example.service.chargingstrategy.ChargeScheduleStrategy.GREEDY;

public class Main {
    public static void main(String[] args) {
        ChargingService chargingService = new ChargingService(GREEDY);
        Truck truck = new Truck(1, 100, 90);
        Truck truck2 = new Truck(2, 100, 30);
        Truck truck3 = new Truck(3, 100, 50);
        Truck truck4 = new Truck(4, 100, 60);
        List<Truck> trucks = List.of(truck, truck2, truck3, truck4);

        Charger charger = new Charger(1, 10);
        Charger charger2 = new Charger(2, 5);
        Charger charger3 = new Charger(3, 20);
        List<Charger> chargers = List.of(charger, charger2, charger3);

        chargingService.getChargingSchedule(trucks, chargers, 10);

    }
}