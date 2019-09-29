package main.lab3.command;

import main.lab1.factory.exceptions.NoSuchModelNameException;
import main.lab1.factory.interfaces.IVehicle;

import java.io.FileWriter;
import java.io.IOException;

public class RowPrintCommand implements IPrintCommand {
    @Override
    public void print(IVehicle vehicle, FileWriter fileWriter) {
        if (vehicle == null || fileWriter == null)
            throw new IllegalArgumentException();

        try {
            fileWriter.write(String.format("Vehicle of brand: %s; Models: ", vehicle.getBrand()));
            for (String modelName : vehicle.getAllModelsNames()) {
                fileWriter.write(String.format("%s %f; ", modelName, vehicle.getModelPrice(modelName)));
            }
        } catch (IOException |
                NoSuchModelNameException e) {
            e.printStackTrace();
        }
    }
}
