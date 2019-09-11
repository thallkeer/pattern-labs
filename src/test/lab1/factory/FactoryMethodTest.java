package test.lab1.factory;

import main.lab1.factory.Car;
import main.lab1.factory.Motorcycle;
import main.lab1.factory.Vehicles;
import main.lab1.factory.exceptions.DuplicateModelNameException;
import main.lab1.factory.exceptions.NoSuchModelNameException;
import main.lab1.factory.factories.MotorcycleFactory;
import main.lab1.factory.interfaces.IVehicle;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class FactoryMethodTest {
    @Test
    public void checkMotorcycleDeepClone() throws CloneNotSupportedException, DuplicateModelNameException, NoSuchModelNameException {
        Motorcycle motik = new Motorcycle("Yamaha", 3);
        motik.setModelName("0","g200");
        motik.setModelPrice("g200",290000);
        motik.setModelName("1","s50");
        motik.setModelPrice("g200",500000);

        Motorcycle motikClone = (Motorcycle) motik.clone();

        Vehicles.printPriceList(motik);
        Vehicles.printPriceList(motikClone);

        motik.setModelPrice("g200", 390000);

        assertNotEquals(motik.getModelPrice("g200"),motikClone.getModelPrice("g200"));
        System.out.println("original price: " + motik.getModelPrice("g200"));
        System.out.println("clone price: " + motikClone.getModelPrice("g200"));
    }

    @Test
    public void checkCarDeepClone() throws CloneNotSupportedException, NoSuchModelNameException, DuplicateModelNameException {
        Car car = new Car("VAZ", 4);
        Initialize(car);
        Vehicles.printPriceList(car);

        Car carClone = (Car) car.clone();
        Vehicles.printPriceList(car);

        car.setModelPrice("2114", 390000);

        assertNotEquals(car.getModelPrice("2114"), carClone.getModelPrice("2114"));

        System.out.println("original price: " + car.getModelPrice("2114"));
        System.out.println("clone price: " + carClone.getModelPrice("2114"));
    }

    public static void main(String[] args) throws NoSuchModelNameException, DuplicateModelNameException {
        IVehicle car = Vehicles.createInstance("VAZ", 4);
        System.out.println(car.getClass());
        Initialize(car);
        Vehicles.printPriceList(car);
        System.out.println("The average price of an " + car.getBrand() + " brand" + " car is " + Vehicles.getAveragePrice(car));

        Vehicles.setFactory(new MotorcycleFactory());
        IVehicle motik = Vehicles.createInstance("Yamaha", 3);
        System.out.println(motik.getClass());

        motik.setModelName("0","g200");
        motik.setModelPrice("g200", 290000);

        //motik.setModelName("g200","g200");

        motik.setModelName("1","s50");
        motik.setModelPrice("s50", 500000);

        Vehicles.printPriceList(motik);
        System.out.println("The average price of an " + motik.getBrand() + " brand" + " car is " + Vehicles.getAveragePrice(motik));
    }

    private static void Initialize(IVehicle vehicle) throws NoSuchModelNameException, DuplicateModelNameException {
        vehicle.setModelName("0","2109");
        vehicle.setModelPrice("2109", 20000);
        vehicle.setModelName("1","2114");
        vehicle.setModelPrice("2114", 100000);
        vehicle.setModelName("2","2115");
        vehicle.setModelPrice("2115", 200000);
        vehicle.setModelName("3","Kopeika");
        vehicle.setModelPrice("Kopeika", 5000);
    }
}
