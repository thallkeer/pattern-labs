package main.lab3.observer;

public interface IObserver {
    void update(ControlRole role);
    void subscribe(IObservable provider);
}
