package main.lab4.dao;

import main.lab1.factory.exceptions.DuplicateModelNameException;
import main.lab1.factory.exceptions.NoSuchModelNameException;
import main.lab1.factory.interfaces.IVehicle;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class VehicleTextDAO implements IDAO<IVehicle> {
    private String path;

    public VehicleTextDAO(String path) {
        this.path = path;
    }

    @Override
    public void write(IVehicle entity) {
        try (FileWriter fw = new FileWriter(path)){
            PrintWriter pw = new PrintWriter(fw);
            pw.println(entity.getClass().getName());
            pw.println(entity.getBrand());
            pw.println(entity.getModelsSize());
            String[] names = entity.getAllModelsNames();
            for (int i = 0; i < names.length; i++) {
                pw.println(names[i] + " " + entity.getModelPrice(names[i]));
            }
        } catch (IOException | NoSuchModelNameException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IVehicle read() {
        try (FileReader fr = new FileReader(path)){
            Scanner scanner = new Scanner(fr);
            String className = scanner.nextLine();
            String brand = scanner.nextLine();
            int modelsSize = Integer.parseInt(scanner.nextLine());
            Class _class = Class.forName(className);
            Constructor ctor = _class.getConstructor(String.class,int.class);
            IVehicle vehicle = (IVehicle) ctor.newInstance(new Object[] {brand, modelsSize});
            for (int i = 0; i < modelsSize; i++) {
                String nextModel = scanner.nextLine();
                String[] modelWithPrice = nextModel.split(" ");
                vehicle.setModelName(Integer.toString(i), modelWithPrice[0]);
                vehicle.setModelPrice(modelWithPrice[0], Double.parseDouble(modelWithPrice[1]));
            }
            return vehicle;
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException | DuplicateModelNameException | NoSuchModelNameException e) {
            e.printStackTrace();
        }
        return null;
    }
}
