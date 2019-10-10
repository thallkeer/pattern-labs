package main.lab3.visitor;

import main.lab1.factory.Car;
import main.lab1.factory.Motorcycle;

public interface IPrintVisitor {
    void visit(Car car);
    void visit(Motorcycle motorcycle);
}
