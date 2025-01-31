package org.example.service.chargingstrategy;

public class ChargeScheduleFactory {
    ChargeScheduleStrategy scheduleStrategy;

    public static ChargeSchedule getChargeSchedule(ChargeScheduleStrategy scheduleStrategy) {
        if (scheduleStrategy == ChargeScheduleStrategy.GREEDY) {
            return new ChargeScheduleGreedy();
        } else {
            throw new RuntimeException("Invalid Strategy");
        }
    }
}
