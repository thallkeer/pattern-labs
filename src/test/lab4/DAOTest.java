package test.lab4;

import main.lab1.factory.Car;
import main.lab1.factory.Vehicles;
import main.lab1.factory.exceptions.NoSuchModelNameException;
import main.lab1.factory.interfaces.IVehicle;
import main.lab4.dao.IDAO;
import main.lab4.dao.VehicleSerializeDAO;
import main.lab4.dao.VehicleTextDAO;
import org.junit.Test;
import test.shared.Utils;

public class DAOTest {
    @Test
    public void TestDAO() throws NoSuchModelNameException {
        Car car = (Car) Utils.initializeVehicle(5);
        System.out.println("----------------ORIGINAL-----------------");
        Vehicles.printVehicle(car);
        IDAO<IVehicle> dao = new VehicleTextDAO("textVehicle.txt");
        dao.write(car);
        System.out.println("----------------TEXT-----------------");
        Vehicles.printVehicle(dao.read());

        dao = new VehicleSerializeDAO("binVehicle.bin");
        dao.write(car);
        System.out.println("----------------SERIALIZED-----------------");
        Vehicles.printVehicle(dao.read());
    }
}
