package main.lab3.strategy;

import main.lab1.factory.Vehicles;
import main.lab1.factory.interfaces.IVehicle;

import java.util.HashMap;

public class SortExecutor {
    public IVehicle[] sort(IVehicleSorter sortStrategy, IVehicle[] vehicles) {
        IVehicle[] sortedVehicles = new IVehicle[vehicles.length];
        System.arraycopy(vehicles,0,sortedVehicles,0,vehicles.length);
        HashMap<IVehicle,Double> pricesMap = Vehicles.getVehiclesAveragePrices(sortedVehicles);
        return sortStrategy.sort(sortedVehicles, pricesMap);
    }
}
