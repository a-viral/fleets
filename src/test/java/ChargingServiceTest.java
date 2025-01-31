import org.example.model.Charger;
import org.example.model.Truck;
import org.example.model.ChargingSchedule;
import org.example.service.ChargingService;
import org.example.service.chargingstrategy.ChargeScheduleStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ChargingServiceTest {
    private ChargingService chargingService;
    private List<Truck> trucks;
    private List<Charger> chargers;

    @BeforeEach
    void setUp() {
        chargingService = new ChargingService(ChargeScheduleStrategy.GREEDY);
    }

    @Test
    void testBasicChargingSchedule() {
        // Given
        trucks = List.of(
                new Truck(1, 100, 20),
                new Truck(2, 100, 30),
                new Truck(3, 100, 50),
                new Truck(4, 100, 60)
        );

        chargers = List.of(
                new Charger(1, 10),
                new Charger(2, 5),
                new Charger(3, 20)
        );

        // When
        ChargingSchedule schedule = chargingService.getChargingSchedule(trucks, chargers, 3);
        // Then
        assertNotNull(schedule);
        Map<Charger, List<Truck>> assignments = schedule.getChargerTruckMap();
        assertFalse(assignments.isEmpty());

        // Verify all trucks are assigned
        int totalAssignedTrucks = assignments.values().stream()
                .mapToInt(List::size)
                .sum();
        assertEquals(1, totalAssignedTrucks);
    }

    @Test
    void testBasicChargingSchedule2() {
        // Given
        trucks = List.of(
                new Truck(1, 100, 20),
                new Truck(2, 100, 30),
                new Truck(3, 100, 50),
                new Truck(4, 100, 60)
        );

        chargers = List.of(
                new Charger(1, 10),
                new Charger(2, 5),
                new Charger(3, 20)
        );

        // When
        ChargingSchedule schedule = chargingService.getChargingSchedule(trucks, chargers, 10);
        // Then
        assertNotNull(schedule);
        Map<Charger, List<Truck>> assignments = schedule.getChargerTruckMap();
        assertFalse(assignments.isEmpty());

        // Verify all trucks are assigned
        int totalAssignedTrucks = assignments.values().stream()
                .mapToInt(List::size)
                .sum();
        assertEquals(4, totalAssignedTrucks);
    }

    @Test
    void testEmptyTruckList() {
        // Given
        trucks = List.of();
        chargers = List.of(new Charger(1, 10));

        // When
        ChargingSchedule schedule = chargingService.getChargingSchedule(trucks, chargers, 3);

        // Then
        assertTrue(schedule.getChargerTruckMap().isEmpty());
    }

    @Test
    void testEmptyChargerList() {
        // Given
        trucks = List.of(new Truck(1, 100, 20));
        chargers = List.of();

        // When
        ChargingSchedule schedule = chargingService.getChargingSchedule(trucks, chargers, 3);

        // Then
        assertTrue(schedule.getChargerTruckMap().isEmpty());
    }

    @Test
    void testNullInputs() {
        // When & Then
        assertThrows(NullPointerException.class,
                () -> chargingService.getChargingSchedule(null, chargers, 3));
        assertThrows(NullPointerException.class,
                () -> chargingService.getChargingSchedule(trucks, null, 3));
    }

    @Test
    void testInsufficientTimeSlots() {
        // Given
        trucks = List.of(
                new Truck(1, 100, 90),  // Will need many time slots
                new Truck(2, 100, 80)
        );
        chargers = List.of(new Charger(1, 10));  // Can charge 10% per slot

        // When
        ChargingSchedule schedule = chargingService.getChargingSchedule(trucks, chargers, 1);

        // Then
        Map<Charger, List<Truck>> assignments = schedule.getChargerTruckMap();
        // At least one truck should be assigned given the time constraint
        assertTrue(assignments.values().stream().mapToInt(List::size).sum() > 0);
    }
}