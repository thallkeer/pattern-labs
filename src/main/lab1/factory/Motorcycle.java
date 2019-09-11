package main.lab1.factory;

import main.lab1.factory.exceptions.DuplicateModelNameException;
import main.lab1.factory.exceptions.ModelPriceOutOfBoundsException;
import main.lab1.factory.exceptions.NoSuchModelNameException;
import main.lab1.factory.interfaces.IVehicle;

import java.util.Objects;

public class Motorcycle implements IVehicle, Cloneable {
    private class Model {
        private String name = null;
        private double price = Double.NaN;
        Model prev = null;
        Model next = null;

        Model(Model next, Model prev, String name, double price) {
            this.name = name;
            this.price = price;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return "model: " + name + " price: " + price;
        }

        Model(String name, double price) {
            this(null, null, name, price);
        }

        double getPrice() {
            return this.price;
        }

        void setPrice(double price) {
            if (price <= 0)
                throw new ModelPriceOutOfBoundsException("Model price must be greater than zero!");
            else
                this.price = price;
        }

        public String getName() {
            return this.name;
        }

        void setName(String name) {
            this.name = name;
        }
    }

    private Model head = null;

    private String brand;
    private int size = 0;

    public Motorcycle(String brand) {
        this.brand = brand;
    }

    public Motorcycle(String brand, int modelsSize) {
        this(brand);
        for (int i = 0; i < modelsSize; i++) {
            try {
                addModel(Integer.toString(i), 0);
            } catch (DuplicateModelNameException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Motorcycle motorcycle = (Motorcycle) super.clone();
        motorcycle.head = null;
        motorcycle.size = 0;
        if (this.head != null) {
            Model node = this.head;
            for (int i = 0; i < this.getModelsSize(); node = node.next, i++) {
                try {
                    motorcycle.addModel(node.name, node.price);
                } catch (DuplicateModelNameException e) {
                    e.printStackTrace();
                }
            }
        }
        return motorcycle;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String getBrand() {
        return this.brand;
    }

    @Override
    public void setModelName(String modelName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        Model model = findModelByName(modelName);
        if (isModelExists(newName))
            throw new DuplicateModelNameException(newName);
        model.setName(newName);
    }

    @Override
    public String[] getAllModelsNames() {
        String[] names = new String[size];
        if (!isEmpty()) {
            Model node = this.head;
            for (int i = 0; i < size; node = node.next, i++)
                names[i] = node.name;
        }
        return names;
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
        double[] prices = new double[size];
        Model node = this.head;
        for (int i = 0; i < size; node = node.next, i++)
            prices[i] = node.price;
        return prices;
    }

    private Model findModelByName(String modelName) throws NoSuchModelNameException {
        Model result = getModelByName(modelName);
        if (result == null)
            throw new NoSuchModelNameException(modelName);
        return result;
    }

    private Model getModelByName(String modelName){
        Model result = null;
        if (!Utils.stringIsNullOrEmpty(modelName) && !isEmpty()) {
            if (this.head.name.equals(modelName)) {
                result = this.head;
            } else {
                for (Model node = this.head.next; node != this.head; node = node.next)
                    if (node.name.equals(modelName)) {
                        result = node;
                        break;
                    }
            }
        }
        return result;
    }

    private boolean isModelExists(String modelName){
      return getModelByName(modelName) != null;
    }

    private boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void addModel(String modelName, double price) throws DuplicateModelNameException {
        if (isModelExists(modelName))
            throw new DuplicateModelNameException(modelName);
        if (price <= 0)
            throw new ModelPriceOutOfBoundsException("Model price must be greater than zero!");
        Model node = new Model(modelName, price);
        if (head == null) {
            head = node;
            head.next = node;
            head.prev = node;
        } else {
            node.prev = head.prev;
            node.next = head;
            head.prev.next = node;
            head.prev = node;
        }
        size++;
    }

    @Override
    public void deleteModel(String modelName) throws NoSuchModelNameException {
        if (head == null)
            return;
        Model modelToDelete = findModelByName(modelName);

        if (modelToDelete.next == modelToDelete.prev)
            head = null;
        else {
            modelToDelete.prev.next = modelToDelete.next;
            modelToDelete.next.prev = modelToDelete.prev;
        }
        size--;
    }

    @Override
    public int getModelsSize() {
        return size;
    }
}
