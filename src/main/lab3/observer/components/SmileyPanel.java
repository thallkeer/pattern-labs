package main.lab3.observer.components;

import main.lab3.observer.ControlRole;
import main.lab3.observer.interfaces.IObservable;
import main.lab3.observer.interfaces.IObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SmileyPanel extends JPanel implements IObservable {
    private List<IObserver> listeners;
    EyeEllipse leftEye;
    EyeEllipse rightEye;
    NoseTriangle nose;
    Mouth mouth;

    public SmileyPanel() {
        listeners = new ArrayList<>();
        leftEye = new EyeEllipse(100, 100, 75, 75, ControlRole.LEFT_EYE);
        rightEye = new EyeEllipse(400, 100, 75, 75, ControlRole.RIGHT_EYE);
        nose = new NoseTriangle(255
                , 250, 50, 50);
        mouth = new Mouth(100, 400, 375, 50);

        this.addAndSubscribe(leftEye);
        this.addAndSubscribe(rightEye);
        this.addAndSubscribe(nose);
        this.addAndSubscribe(mouth);
    }

    private void addAndSubscribe(ObserverComponent component) {
        this.add(component);
        this.addObserver(component);
    }

    @Override
    public void addObserver(IObserver observer) {
        if (observer != null) {
            this.listeners.add(observer);
            observer.subscribe(this);
        }
    }

    @Override
    public void notify(ControlRole role) {
        this.listeners.forEach(observer -> observer.update(role));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.YELLOW);
        g.fillOval(40, 20, 500, 500);
    }
}
