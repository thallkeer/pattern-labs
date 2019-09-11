package main.lab1.factory.factories;

import main.lab1.factory.Car;
import main.lab1.factory.interfaces.ITransportFactory;
import main.lab1.factory.interfaces.IVehicle;

public class CarFactory implements ITransportFactory {
    @Override
    public IVehicle createInstance(String brand, int modelsSize) {
        return new Car(brand, modelsSize);
    }
}
