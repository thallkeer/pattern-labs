package main.lab3.observer.interfaces;

import main.lab3.observer.ControlRole;

public interface IObservable {
    void addObserver(IObserver observer);
    void notify(ControlRole role);
}
