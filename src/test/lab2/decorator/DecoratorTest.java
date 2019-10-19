package test.lab2.decorator;

import main.lab1.factory.Vehicles;
import main.lab1.factory.exceptions.DuplicateModelNameException;
import main.lab1.factory.exceptions.NoSuchModelNameException;
import main.lab1.factory.interfaces.IVehicle;
import main.lab3.chain.ColumnPrinter;
import main.lab3.chain.IVehiclePrinter;
import main.lab3.chain.RowPrinter;
import org.junit.Test;
import test.shared.Utils;

import static test.shared.Utils.getRandomDouble;

public class DecoratorTest {

    @Test
    public void createSynchronizedVehicle() throws NoSuchModelNameException, DuplicateModelNameException {
        IVehicle vehicle = Utils.initializeVehicle(22);

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
