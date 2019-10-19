package main.lab4.dao;

import main.lab1.factory.interfaces.IVehicle;

import java.io.*;

public class VehicleSerializeDAO implements IDAO<IVehicle> {
    private String path;

    public VehicleSerializeDAO(String path){
        this.path = path;
    }

    @Override
    public void write(IVehicle entity) {
        try (OutputStream os = new FileOutputStream(path)){
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IVehicle read() {
        try (InputStream is = new FileInputStream(path)){
            ObjectInputStream ois = new ObjectInputStream(is);
            return (IVehicle) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
