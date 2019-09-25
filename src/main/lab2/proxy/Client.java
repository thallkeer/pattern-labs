package main.lab2.proxy;

public class Client {
    private IMultiplier service;

    public Client(IMultiplier service) {
        this.service = service;
    }

    public double getMultiplicationResult(double a, double b) {
        return service.multiply(a, b);
    }
}
