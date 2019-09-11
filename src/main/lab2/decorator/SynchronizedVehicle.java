package main.lab2.decorator;

import main.lab1.factory.exceptions.DuplicateModelNameException;
import main.lab1.factory.exceptions.NoSuchModelNameException;
import main.lab1.factory.interfaces.IVehicle;

public class SynchronizedVehicle implements IVehicle {
    private final IVehicle vehicle;

    public SynchronizedVehicle(IVehicle vehicle) {
        this.vehicle = vehicle;
    }


    @Override
    public synchronized void setBrand(String brand) {
        vehicle.setBrand(brand);
    }

    @Override
    public synchronized void setModelName(String modelName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        vehicle.setModelName(modelName, newName);
    }

    @Override
    public synchronized void setModelPrice(String modelName, double price) throws NoSuchModelNameException {
        vehicle.setModelPrice(modelName, price);
    }

    @Override
    public synchronized void addModel(String modelName, double price) throws DuplicateModelNameException {
        vehicle.addModel(modelName, price);
    }

    @Override
    public synchronized void deleteModel(String modelName) throws NoSuchModelNameException {
        vehicle.deleteModel(modelName);
    }

    @Override
    public synchronized String getBrand() {
        return vehicle.getBrand();
    }

    @Override
    public synchronized int getModelsSize() {
        return vehicle.getModelsSize();
    }

    @Override
    public synchronized double getModelPrice(String modelName) throws NoSuchModelNameException {
        return vehicle.getModelPrice(modelName);
    }

    @Override
    public synchronized String[] getAllModelsNames() {
        return vehicle.getAllModelsNames();
    }

    @Override
    public synchronized double[] getAllModelsPrices() {
        return vehicle.getAllModelsPrices();
    }
}