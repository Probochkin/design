package ru.job4j.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class Parking {
    List<Transport> transportsCar = new ArrayList<>();
    List<Transport> transportsTruck = new ArrayList<>();
    private int locationCar;
    private int locationTruck;

    public  Parking(int locationCar, int locationTruck) {
        this.locationCar = locationCar;
        this.locationTruck = locationTruck;
    }

    private boolean controlPark(Transport transport) {
       if (transport.getSize() > 1) {
           return false;
       } else {
           return true;
       }
    }

    public boolean transportEntry(Transport transport) {
        controlPark(transport);
        boolean result = false;
       int size = transport.getSize();
        if (size > 1) {
            if (locationTruck > 0) {
                transportsTruck.add(transport);
                locationTruck--;
                result = true;
            } else if (locationCar - size >= 0) {
                    transportsCar.add(transport);
                    locationCar = locationCar - size;
            }
        } else if (locationCar > 0) {
            transportsCar.add(transport);
            locationCar--;
            result = true;
        }

        return result;
    }
    public boolean transportExit(Transport transport) {
        boolean result = false;
        int size = transport.getSize();
        if (size > 1) {
            result = transportsTruck.remove(transport);
            if (!result) {
                result = transportsCar.remove(transport);
            }
        } else {
           result = transportsCar.remove(transport);
        }
        return result;
    }
}
