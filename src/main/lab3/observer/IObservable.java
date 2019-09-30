package main.lab3.observer;

public interface IObservable {
    void addObserver(IObserver observer);
    void notify(ControlRole role);
}
