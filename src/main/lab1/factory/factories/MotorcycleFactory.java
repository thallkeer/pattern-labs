package main.lab1.factory.factories;

import main.lab1.factory.Motorcycle;
import main.lab1.factory.interfaces.ITransportFactory;
import main.lab1.factory.interfaces.IVehicle;

public class MotorcycleFactory implements ITransportFactory {
    @Override
    public IVehicle createInstance(String brand, int modelsSize) {
        return new Motorcycle(brand,modelsSize);
    }
}
