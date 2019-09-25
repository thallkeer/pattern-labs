package main.lab3.chain;

import main.lab1.factory.Vehicles;
import main.lab1.factory.exceptions.NoSuchModelNameException;
import main.lab1.factory.factories.CarFactory;
import main.lab1.factory.factories.MotorcycleFactory;
import main.lab1.factory.interfaces.IVehicle;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ColumnPrinter cp = new ColumnPrinter();
        RowPrinter rp = new RowPrinter();
        cp.setNext(rp);
        rp.setNext(cp);

        Vehicles.setFactory(new CarFactory());
        IVehicle vehicleWithModelsCountLessOrEquals3 = getVehicle("Some car",2);

        Vehicles.setFactory(new MotorcycleFactory());
        IVehicle vehicleWithModelsCountMoreThan3 = getVehicle("Some motorcycle",4);

        cp.printVehicleToFile(vehicleWithModelsCountLessOrEquals3);
        rp.printVehicleToFile(vehicleWithModelsCountMoreThan3);
    }

    private static IVehicle getVehicle(String vehicleName, int modelsSize) {
        IVehicle vehicle = Vehicles.createInstance("Some car", modelsSize);
        try {
            for (int i = 0; i < vehicle.getModelsSize(); i++)
                vehicle.setModelPrice(String.valueOf(i), 20000000 - (i * 100));
        } catch (NoSuchModelNameException e) {
            e.printStackTrace();
        }
        return vehicle;
    }
}
