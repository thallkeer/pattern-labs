package main.lab3.strategy;

import main.lab1.factory.interfaces.IVehicle;

import java.util.HashMap;

public interface IVehicleSorter {
    IVehicle[] sort(IVehicle[] vehicles, HashMap<IVehicle, Double> pricesMap);
}