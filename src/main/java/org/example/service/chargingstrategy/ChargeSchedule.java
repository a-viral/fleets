package org.example.service.chargingstrategy;

import org.example.model.Charger;
import org.example.model.ChargingSchedule;
import org.example.model.Truck;

import java.util.List;

public interface ChargeSchedule {
    ChargingSchedule getChargingSchedule(List<Truck> trucks, List<Charger> chargers, int hours);
}
