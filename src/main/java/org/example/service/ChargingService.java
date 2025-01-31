package org.example.service;

import org.example.model.Charger;
import org.example.model.ChargingSchedule;
import org.example.model.Truck;
import org.example.service.chargingstrategy.ChargeSchedule;
import org.example.service.chargingstrategy.ChargeScheduleFactory;
import org.example.service.chargingstrategy.ChargeScheduleStrategy;

import java.util.List;

public class ChargingService {
    private ChargeScheduleStrategy chargeScheduleStrategy;
    private ChargeSchedule chargeSchedule;

    public ChargingService(ChargeScheduleStrategy chargeScheduleStrategy) {
        this.chargeScheduleStrategy = chargeScheduleStrategy;
        this.chargeSchedule = ChargeScheduleFactory.getChargeSchedule(this.chargeScheduleStrategy);
    }

    public ChargingSchedule getChargingSchedule(List<Truck> trucks, List<Charger> chargers, int hours) {
        ChargingSchedule chargingSchedule = chargeSchedule.getChargingSchedule(trucks, chargers, hours);
        System.out.println(chargingSchedule);
        return chargingSchedule;
    }
}
