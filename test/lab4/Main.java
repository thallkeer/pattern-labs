package lab4;


import main.lab1.factory.Car;
import main.lab1.factory.interfaces.IVehicle;
import main.lab4.dao.IDAO;
import main.lab4.dao.VehicleSerializeDAO;
import main.lab4.dao.VehicleTextDAO;
import shared.Utils;

public class Main {
    public static void main(String[] args) {
        //TODO: REFACTOR
        IDAO dao = new VehicleTextDAO("E:\\JAVA_Projects\\veh.txt");
        IVehicle vehicle = (Car)Utils.initializeVehicle(5);
        dao.write(vehicle);
        System.out.println("From text: \n" + dao.read());
        System.out.println("----------------------------------------");
        dao = new VehicleSerializeDAO("E:\\JAVA_Projects\\veh.bin");
        dao.write(vehicle);
        System.out.println("From bin: \n" + dao.read());
    }
}
