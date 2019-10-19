package test.shared;

import main.lab1.factory.Vehicles;
import main.lab1.factory.exceptions.DuplicateModelNameException;
import main.lab1.factory.interfaces.IVehicle;

public class Utils {
    static int number = 0;
    public static IVehicle initializeVehicle(int modelSize) {
        IVehicle vehicle = Vehicles.createInstance("Some vehicle" + number, 0);
        try {
            for (int i = 0; i < modelSize; i++) {
                vehicle.addModel("Model №" + i, getRandomDouble());
            }
            number++;
        } catch (DuplicateModelNameException ex) {
            ex.printStackTrace();
        }
        return vehicle;
    }

    public static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public static double getRandomDouble() {
        return (Math.random() * 10 + 1) * 100000;
    }
}
