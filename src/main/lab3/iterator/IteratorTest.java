package main.lab3.iterator;

import main.lab1.factory.Car;
import org.junit.Test;
import test.lab2.decorator.DecoratorTest;
import test.shared.Utils;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

public class IteratorTest {

    @Test
    public void testIterator(){
        Car car = (Car) Utils.initializeVehicle(15);
        System.out.println("Models size is " + car.getModelsSize());

        Iterator iterator = car.createCarIterator();
        int index = 0;

        while(iterator.hasNext()){
            Car.Model iteratorModel = (Car.Model) iterator.next();
            Car.Model indexedModel = car.getByIndex(index);

            assertEquals(iteratorModel.getName(),indexedModel.getName());
            assertEquals(iteratorModel.getPrice(), indexedModel.getPrice(),0);
            index++;
            System.out.println(iteratorModel);
        }
    }
}
