package main.lab3.chain;

import main.lab1.factory.interfaces.IVehicle;

public class RowPrinter implements IVehiclePrinter {
    private IVehiclePrinter next;

    @Override
    public void setNext(IVehiclePrinter nextPrinter) {
        this.next = nextPrinter;
    }

    @Override
    public void printVehicleToFile(IVehicle vehicle) {
        if (vehicle.getModelsSize() <= 3) {
            //TODO: print all vehicle properties in one row
        } else if (this.next != null)
            this.next.printVehicleToFile(vehicle);
    }
}
