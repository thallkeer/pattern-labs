package main.lab1.factory.exceptions;

public class NoSuchModelNameException extends Exception {
    public NoSuchModelNameException(String message){
        super("No such model name: " + message);
    }
}
