package lab3.memento;

import main.lab1.factory.Car;
import main.lab1.factory.Vehicles;
import main.lab1.factory.exceptions.NoSuchModelNameException;
import org.junit.Test;
import shared.Utils;

import java.util.Iterator;

import static org.junit.Assert.assertNotEquals;

public class MementoTest {

    @Test
    public void testMemento() throws NoSuchModelNameException {
        Car car = (Car) Utils.initializeVehicle(20);
        car.createMemento();

        Vehicles.printPriceList(car);

        Iterator iterator = car.createCarIterator();

        car.setBrand("TEST BRAND");
        int index = 0;
        while(iterator.hasNext()) {
            Car.Model model = (Car.Model) iterator.next();
            model.setName("TEST" + index);
            model.setPrice(2281337);
            index++;
        }

        Vehicles.printPriceList(car);
        Vehicles.printPriceList(car.setMemento());
        assertNotEquals(car.getByIndex(5),car.setMemento().getByIndex(5));
    }
}
