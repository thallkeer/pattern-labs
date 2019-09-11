package main.lab1.factory.interfaces;

public interface ITransportFactory {
    IVehicle createInstance(String brand, int modelsSize);
}
