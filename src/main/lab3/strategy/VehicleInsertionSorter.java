package main.lab3.strategy;

import main.lab1.factory.interfaces.IVehicle;

import java.util.HashMap;

public class VehicleInsertionSorter implements IVehicleSorter {
    @Override
    public IVehicle[] sort(IVehicle[] vehicles, HashMap<IVehicle,Double> pricesMap) {
        for (int left = 0; left < vehicles.length; left++) {
            // Вытаскиваем значение элемента
            IVehicle curVeh = vehicles[left];
            double value = pricesMap.get(curVeh);
            // Перемещаемся по элементам, которые перед вытащенным элементом
            int i = left - 1;
            for (; i >= 0; i--) {
                // Если вытащили значение меньшее — передвигаем больший элемент дальше
                if (value < pricesMap.get(vehicles[i])) {
                    vehicles[i + 1] = vehicles[i];
                } else {
                    // Если вытащенный элемент больше — останавливаемся
                    break;
                }
            }
            // В освободившееся место вставляем вытащенное значение
            vehicles[i + 1] = curVeh;
        }
        return vehicles;
    }
}
