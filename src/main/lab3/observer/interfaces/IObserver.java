package main.lab3.observer.interfaces;

import main.lab3.observer.ControlRole;

public interface IObserver {
    void update(ControlRole role);
    void subscribe(IObservable provider);
}
