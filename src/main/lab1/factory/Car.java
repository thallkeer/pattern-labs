package main.lab1.factory;

import main.lab1.factory.exceptions.DuplicateModelNameException;
import main.lab1.factory.exceptions.ModelPriceOutOfBoundsException;
import main.lab1.factory.exceptions.NoSuchModelNameException;
import main.lab1.factory.interfaces.IVehicle;

import java.util.Arrays;
import java.util.Objects;

public class Car implements IVehicle, Cloneable {
    private class Model implements Cloneable {
        private String name;
        private double price;

        public Model(String name, double price) {
            this.name = name;
            this.price = price;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            Model model = (Model) super.clone();
            model.name = this.name;
            model.price = this.price;
            return model;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            if (price <= 0)
                throw new ModelPriceOutOfBoundsException("Model price must be greater than zero!");
            else
                this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private String brand;
    private Model[] models;

    public Car(String brand) {
        this(brand, 0);
    }

    public Car(String brand, int modelsSize) {
        this.brand = brand;
        this.models = new Model[modelsSize];
        for (int i = 0; i < modelsSize; i++) {
            models[i] = new Model(Integer.toString(i), 0);
        }
    }

        @Override
    public Object clone() throws CloneNotSupportedException {
        Car car = (Car) super.clone();
        car.models = this.models.clone();
        for (int i = 0; i < this.getModelsSize(); i++) {
            car.models[i] = (Model) this.models[i].clone();
        }
        return car;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setModelName(String modelName, String newName) throws DuplicateModelNameException, NoSuchModelNameException {
        Model model = findModelByName(modelName);
        if (getModelsSize() != 0 && indexOfModel(newName) != -1)
            throw new DuplicateModelNameException(newName);
        model.setName(newName);
    }

    @Override
    public String[] getAllModelsNames() {
        String[] allNames = new String[models.length];
        for (int i = 0; i < models.length; i++)
            allNames[i] = models[i].getName();
        return allNames;
    }

    public void printModelsNames() {
        for (String name :
                getAllModelsNames()) {
            System.out.println(name);
        }
    }

    @Override
    public double getModelPrice(String modelName) throws NoSuchModelNameException {
        Model model = findModelByName(modelName);
        return model.getPrice();
    }

    @Override
    public void setModelPrice(String modelName, double price) throws NoSuchModelNameException {
        Model model = findModelByName(modelName);
        model.setPrice(price);
    }

    @Override
    public double[] getAllModelsPrices() {
        double[] prices = new double[models.length];
        for (int i = 0; i < models.length; i++) {
            prices[i] = models[i].getPrice();
        }
        return prices;
    }

    @Override
    public void addModel(String modelName, double price) throws DuplicateModelNameException {
        if (getModelsSize() != 0 && indexOfModel(modelName) != -1)
            throw new DuplicateModelNameException(modelName);
        if (price <= 0)
            throw new ModelPriceOutOfBoundsException("Model price must be greater than zero!");
        int size = getModelsSize();
        this.models = Arrays.copyOf(this.models, size + 1);
        this.models[size] = new Model(modelName, price);
    }

    @Override
    public void deleteModel(String modelName) throws NoSuchModelNameException {
        int size = getModelsSize();
        if (size == 0)
            return;
        int index = indexOfModel(modelName);
        if (index == -1)
            throw new NoSuchModelNameException(modelName);

        Model[] newModels = new Model[size - 1];
        System.arraycopy(this.models, 0, newModels, 0, index);
        if (size != index) {
            System.arraycopy(this.models, index + 1, newModels, index, size - index - 1);
        }
        this.models = newModels;
    }

    @Override
    public int getModelsSize() {
        return models.length;
    }

    private Model findModelByName(String modelName) throws NoSuchModelNameException {
        int index = indexOfModel(modelName);
        if (index == -1)
            throw new NoSuchModelNameException(modelName);
        return models[index];
    }

    private int indexOfModel(String modelName) {
        if (!Utils.stringIsNullOrEmpty(modelName)) {
            for (int i = 0; i < getModelsSize(); i++) {
                if (this.models[i].getName().equals(modelName))
                    return i;
            }
        }
        return -1;
    }
}
