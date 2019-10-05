package main.lab1.factory;

import main.lab1.factory.exceptions.DuplicateModelNameException;
import main.lab1.factory.exceptions.ModelPriceOutOfBoundsException;
import main.lab1.factory.exceptions.NoSuchModelNameException;
import main.lab1.factory.interfaces.IVehicle;
import main.lab3.command.IPrintCommand;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

public class Car implements IVehicle, Cloneable, Serializable {
    private String brand;
    private Model[] models;
    private IPrintCommand printCommand;
    private static CarMemento memento;

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
    public String toString() {
        return brand + "\t" + Vehicles.getAveragePrice(this);
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

    public void setPrintCommand(IPrintCommand printCommand) {
        this.printCommand = printCommand;
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

    public Model getByIndex(int index) {
        if (index < 0 || index >= getModelsSize())
            throw new IndexOutOfBoundsException();
        return models[index];
    }

    public void print(FileWriter fileWriter) {
        if (fileWriter == null)
            throw new IllegalArgumentException("file writer");
        if (this.printCommand != null)
            this.printCommand.print(this, fileWriter);
    }

    public static class Model implements Cloneable, Serializable {
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

        @Override
        public String toString() {
            return name + " " + price;
        }
    }

    public Iterator createCarIterator() {
        return new CarIterator();
    }

    class CarIterator implements Iterator<Car.Model> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < getModelsSize();
        }

        @Override
        public Car.Model next() {
            return this.hasNext() ? models[index++] : null;
        }
    }

    public void createMemento() {
        CarMemento.setAuto(this);
    }

    public Car setMemento() {
        return CarMemento.getAuto();
    }


    public static class CarMemento {
        private static byte[] inMemoryCar;

        public static void setAuto(Car car) {
            try (
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)
            ) {
                objectOutputStream.writeObject(car);
                inMemoryCar = outputStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static Car getAuto() {
            try (
                    ByteArrayInputStream inputStream = new ByteArrayInputStream(inMemoryCar);
                    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)
                )  {
                return (Car) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

