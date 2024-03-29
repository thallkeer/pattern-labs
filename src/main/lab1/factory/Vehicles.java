package main.lab1.factory;

import main.lab1.factory.exceptions.NoSuchModelNameException;
import main.lab1.factory.factories.CarFactory;
import main.lab1.factory.interfaces.ITransportFactory;
import main.lab1.factory.interfaces.IVehicle;
import main.lab2.decorator.SynchronizedVehicle;

import java.util.HashMap;

public class Vehicles {
    private static ITransportFactory factory = new CarFactory();

    public static void setFactory(ITransportFactory newFactory) {
        factory = newFactory;
    }

    public static IVehicle createInstance(String name, int size) {
        return factory.createInstance(name, size);
    }

    public static double getAveragePrice(IVehicle vehicle) {
        if (vehicle.getModelsSize() == 0)
            return 0;
        double sum = 0;
        for (double price :
                vehicle.getAllModelsPrices()) {
            sum += price;
        }
        return sum / vehicle.getModelsSize();
    }

    public static HashMap<IVehicle,Double> getVehiclesAveragePrices(IVehicle[] vehicles) {
        HashMap<IVehicle, Double> averagePriceByVehicle = new HashMap<>();
        for (IVehicle vehicle :
                vehicles) {
            averagePriceByVehicle.put(vehicle, Vehicles.getAveragePrice(vehicle));
        }
        return averagePriceByVehicle;
    }

    public static void printVehicle(IVehicle vehicle){
        System.out.println(vehicle);
        try {
            printPriceList(vehicle);
        } catch (NoSuchModelNameException e) {
            e.printStackTrace();
        }
    }

    public static void printPriceList(IVehicle vehicle) throws NoSuchModelNameException {
        for (String modelName : vehicle.getAllModelsNames())
            System.out.println("model: " + modelName + " price: " + vehicle.getModelPrice(modelName));
    }

    public static SynchronizedVehicle getSynchronizedVehicle(IVehicle vehicle) {
        return new SynchronizedVehicle(vehicle);
    }
}



