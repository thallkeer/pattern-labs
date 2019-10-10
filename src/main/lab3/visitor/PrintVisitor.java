package main.lab3.visitor;

import main.lab1.factory.Car;
import main.lab1.factory.Motorcycle;
import main.lab3.command.ColumnPrintCommand;
import main.lab3.command.IPrintCommand;
import main.lab3.command.RowPrintCommand;

import java.io.FileWriter;
import java.io.IOException;

public class PrintVisitor implements IPrintVisitor {

    @Override
    public void visit(Car car) {
        IPrintCommand columnPrintCommand = new RowPrintCommand();
        car.setPrintCommand(columnPrintCommand);

        try (FileWriter fw = new FileWriter("CarVisit.txt")) {
            car.print(fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(Motorcycle motorcycle) {
        IPrintCommand columnPrintCommand = new ColumnPrintCommand();

        try (FileWriter fw = new FileWriter("MotorcycleVisit.txt")) {
            columnPrintCommand.print(motorcycle,fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
