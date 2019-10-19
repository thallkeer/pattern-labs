package  lab3.strategy;

import main.lab1.factory.interfaces.IVehicle;
import main.lab3.strategy.SortExecutor;
import main.lab3.strategy.VehicleInsertionSorter;
import main.lab3.strategy.VehicleMergeSorter;
import  shared.Utils;

import java.util.Arrays;

public class SortTest {
    public static void main(String[] args) {
        IVehicle[] vehicles = new IVehicle[5];
        for (int i = 0; i < vehicles.length; i++) {
            vehicles[i] = Utils.initializeVehicle(Utils.rnd(5, 10));
        }

        SortExecutor sortExecuter = new SortExecutor();

        System.out.println(Arrays.toString(vehicles));
        System.out.println("---Merge sort---");
        System.out.println(Arrays.toString(sortExecuter.sort(new VehicleMergeSorter(), vehicles)));
        System.out.println("---Insertion sort---");
        System.out.println(Arrays.toString(sortExecuter.sort(new VehicleInsertionSorter(), vehicles)));
    }
}
