package test.lab3.command;

import main.lab1.factory.Car;
import main.lab1.factory.Vehicles;
import main.lab1.factory.interfaces.IVehicle;
import main.lab3.command.ColumnPrintCommand;
import main.lab3.command.IPrintCommand;
import main.lab3.command.RowPrintCommand;
import org.junit.Before;
import org.junit.Test;
import test.lab2.decorator.DecoratorTest;
import test.shared.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class CommandTest {
    @Before
    public void setLocale(){
        Locale.setDefault(Locale.US);
    }

    @Test
    public void TestPrintCommand() {
        Car car = (Car) Utils.initializeVehicle(10);

        IPrintCommand rowPrintCommand = new RowPrintCommand();
        IPrintCommand columnPrintCommand = new ColumnPrintCommand();

        car.setPrintCommand(rowPrintCommand);

        try (FileWriter fw = new FileWriter("RowPrintCommand.txt")) {
            car.print(fw);
        } catch (IOException e) {
            e.printStackTrace();
        }

        car.setPrintCommand(columnPrintCommand);

        try (FileWriter fw = new FileWriter("ColumnPrintCommand.txt")) {
            car.print(fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
