package  lab3.chainOfResponsibility;

import main.lab1.factory.interfaces.IVehicle;
import main.lab3.chain.ColumnPrinter;
import main.lab3.chain.IVehiclePrinter;
import main.lab3.chain.RowPrinter;
import org.junit.Before;
import org.junit.Test;
import  lab2.decorator.DecoratorTest;

import java.util.Locale;

import static  shared.Utils.initializeVehicle;

public class CORTest {
    @Before
    public void setLocale(){
        Locale.setDefault(Locale.US);
    }

    @Test
    public void testChainOfResponsibilityRowPrinter(){
        IVehicle vehicle = initializeVehicle(3);
        IVehiclePrinter vehicleColumnPrinter = new ColumnPrinter();
        vehicleColumnPrinter.setNext(new RowPrinter());
        vehicleColumnPrinter.printVehicleToFile(vehicle);
    }
    @Test
    public void testChainOfResponsibilityColumnPrinter(){
        IVehicle vehicle = initializeVehicle(7);
        IVehiclePrinter vehicleRowPrinter = new RowPrinter();
        vehicleRowPrinter.setNext(new ColumnPrinter());
        vehicleRowPrinter.printVehicleToFile(vehicle);
    }
}
