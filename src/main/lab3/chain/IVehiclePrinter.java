package main.lab3.chain;

import main.lab1.factory.interfaces.IVehicle;

public interface IVehiclePrinter {
    void setNext(IVehiclePrinter nextPrinter);
    void printVehicleToFile(IVehicle vehicle);
}
