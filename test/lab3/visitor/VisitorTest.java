package  lab3.visitor;

import main.lab1.factory.Car;
import main.lab1.factory.Motorcycle;
import main.lab1.factory.Vehicles;
import main.lab1.factory.factories.MotorcycleFactory;
import main.lab3.visitor.IPrintVisitor;
import main.lab3.visitor.PrintVisitor;
import org.junit.Test;
import  shared.Utils;

public class VisitorTest {
    @Test
    public void testVisitor() {
        Car car = (Car) Utils.initializeVehicle(5);
        Vehicles.setFactory(new MotorcycleFactory());
        Motorcycle motorcycle = (Motorcycle) Utils.initializeVehicle(7);
        IPrintVisitor visitor = new PrintVisitor();
        visitor.visit(car);
        visitor.visit(motorcycle);
    }
}
