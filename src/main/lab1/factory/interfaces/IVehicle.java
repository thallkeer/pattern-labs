package main.lab1.factory.interfaces;

import main.lab1.factory.exceptions.DuplicateModelNameException;
import main.lab1.factory.exceptions.NoSuchModelNameException;

public interface IVehicle {
    void setBrand(String brand);
    String getBrand();

    void setModelName(String modelName, String newName) throws NoSuchModelNameException, DuplicateModelNameException;
    String[] getAllModelsNames();
    double getModelPrice(String modelName) throws NoSuchModelNameException;
    void setModelPrice(String modelName, double price) throws NoSuchModelNameException;
    double[] getAllModelsPrices();
    void addModel(String modelName, double price) throws DuplicateModelNameException;
    void deleteModel(String modelName) throws NoSuchModelNameException;
    int getModelsSize();
}
