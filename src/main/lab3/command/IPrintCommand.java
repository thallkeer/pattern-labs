package main.lab3.command;

import main.lab1.factory.interfaces.IVehicle;

import java.io.FileWriter;

public interface IPrintCommand {
    void print(IVehicle vehicle, FileWriter fileWriter);
}
