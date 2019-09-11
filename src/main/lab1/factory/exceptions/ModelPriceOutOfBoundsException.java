package main.lab1.factory.exceptions;

public class ModelPriceOutOfBoundsException extends RuntimeException {
    public ModelPriceOutOfBoundsException(String message){
        super(message);
    }
}
