package main.lab3.command;

import main.lab1.factory.exceptions.NoSuchModelNameException;
import main.lab1.factory.interfaces.IVehicle;
import main.lab3.chain.ColumnPrinter;
import main.lab3.chain.IVehiclePrinter;

import java.io.FileWriter;
import java.io.IOException;

public class ColumnPrintCommand implements IPrintCommand {
    @Override
    public void print(IVehicle vehicle, FileWriter fileWriter) {
        if (vehicle == null || fileWriter == null)
            throw new IllegalArgumentException();

        try {
            fileWriter.write(String.format("Vehicle of brand: %s\n", vehicle.getBrand()));

            for (String modelName : vehicle.getAllModelsNames()) {
                fileWriter.write(String.format("model: %s price: %f\n", modelName, vehicle.getModelPrice(modelName)));
            }
        } catch (IOException | NoSuchModelNameException e) {
            e.printStackTrace();
        }
    }
}
