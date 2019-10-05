package main.lab3.strategy;

import main.lab1.factory.interfaces.IVehicle;

import java.util.HashMap;

public class VehicleMergeSorter implements IVehicleSorter {
    private HashMap<IVehicle, Double> pricesMap;

    public VehicleMergeSorter(){
        this.pricesMap = new HashMap<>();
    }

    @Override
    public IVehicle[] sort(IVehicle[] vehicles, HashMap<IVehicle,Double> pricesMap) {
        this.pricesMap = pricesMap;
        mergeSort(vehicles, vehicles.length);
        return vehicles;
    }

    private void merge(
            IVehicle[] a, IVehicle[] l, IVehicle[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (pricesMap.get(l[i]) <= pricesMap.get(r[j])) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    private void mergeSort(IVehicle[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        IVehicle[] l = new IVehicle[mid];
        IVehicle[] r = new IVehicle[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }
}
