package ru.job4j.lsp.parking;

import static org.junit.Assert.*;
import org.junit.Test;



public class ParkingTest {

    @Test
    public void parkCar() {
        Transport car = new Car("op435p");
        Parking parking = new Parking(5, 1);
        parking.transportEntry(car);
        assertEquals(parking.transportsCar.get(0), car);
    }

    @Test
    public void parkTruck() {
        Transport truck = new Truck("op435p", 2);
        Parking parking = new Parking(5, 1);
        parking.transportEntry(truck);
        assertEquals(parking.transportsTruck.get(0), truck);
    }

    @Test
    public void parkTruckThenNoLocationToTruck() {
        Transport truck = new Truck("op435p", 2);
        Parking parking = new Parking(5, 0);
        parking.transportEntry(truck);
        assertEquals(parking.transportsCar.get(0), truck);
    }

    @Test
    public void parkTruckThenNoLocation() {
        Transport truck = new Truck("op435p", 2);
        Parking parking = new Parking(1, 0);
        assertEquals(parking.transportEntry(truck), false);
    }

    @Test
    public void parkTruckExitFromCarLoc() {
        Transport truck = new Truck("op435p", 2);
        Parking parking = new Parking(5, 0);
        parking.transportEntry(truck);
        assertEquals(parking.transportExit(truck), true);
    }

    @Test
    public void parkTruckExitFromTruckLoc() {
        Transport truck = new Truck("op435p", 2);
        Parking parking = new Parking(5, 1);
        parking.transportEntry(truck);
        assertEquals(parking.transportExit(truck), true);
    }
}