package test.lab2.decorator;

import main.lab1.factory.Vehicles;
import main.lab1.factory.exceptions.DuplicateModelNameException;
import main.lab1.factory.exceptions.NoSuchModelNameException;
import main.lab1.factory.factories.CarFactory;
import main.lab1.factory.interfaces.IVehicle;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

public class DecoratorTest {
    private IVehicle initializeVehicle() {
        IVehicle vehicle = Vehicles.createInstance("Some vehicle", 5);
        try {
            for (int i = 0; i < 22; i++) {
                vehicle.addModel("Model №" + i, getRandomDouble());
            }
        } catch (DuplicateModelNameException ex) {
            ex.printStackTrace();
        }
        return vehicle;
    }

    private double getRandomDouble() {
        return (Math.random() * 10 + 1) * 100000;
    }

    @Test
    public void createSynchronizedVehicle() throws NoSuchModelNameException, DuplicateModelNameException {
        IVehicle vehicle = initializeVehicle();

        IVehicle synchronizedVehicle = Vehicles.getSynchronizedVehicle(vehicle);
        synchronizedVehicle.setBrand("COOL BRAND YEAH");
        synchronizedVehicle.setModelPrice("Model №" + 5, 228133714);
        synchronizedVehicle.setModelName("Model №" + 3, "VeRy CooL mOdeLll");
        for (int i = 0; i < 5; i++) {
            synchronizedVehicle.addModel("One more model " + i, getRandomDouble());
        }
        Vehicles.printPriceList(synchronizedVehicle);

        System.out.printf("Vehicle of brand %s average price is %s", synchronizedVehicle.getBrand(), Vehicles.getAveragePrice(synchronizedVehicle));
        System.out.println();
        System.out.println(synchronizedVehicle.getClass());

        Vehicles.printPriceList(vehicle);
        System.out.printf("Vehicle of brand %s average price is %s", vehicle.getBrand(), Vehicles.getAveragePrice(vehicle));
        System.out.println();
        System.out.println(vehicle.getClass());
    }
}
