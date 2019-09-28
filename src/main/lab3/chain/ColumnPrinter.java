package main.lab3.chain;

import main.lab1.factory.exceptions.NoSuchModelNameException;
import main.lab1.factory.interfaces.IVehicle;

import java.io.FileWriter;
import java.io.IOException;

public class ColumnPrinter implements IVehiclePrinter {
    private IVehiclePrinter next;

    @Override
    public void setNext(IVehiclePrinter nextPrinter) {
        this.next = nextPrinter;
    }

    @Override
    public void printVehicleToFile(IVehicle vehicle) {
        if (vehicle.getModelsSize() > 3) {
            try (FileWriter fw = new FileWriter("VehiclesInColumn.txt")) {
                fw.write(String.format("Vehicle of brand: %s\n",vehicle.getBrand()));
                for (String modelName : vehicle.getAllModelsNames()) {
                    fw.write(String.format("model: %s price: %f\n", modelName, vehicle.getModelPrice(modelName)));
                }
            } catch (IOException | NoSuchModelNameException e) {
                e.printStackTrace();
            }
        } else if (this.next != null)
            this.next.printVehicleToFile(vehicle);
    }
}
