package main.lab3.observer.components;

import main.lab3.observer.ControlRole;
import main.lab3.observer.interfaces.IObservable;
import main.lab3.observer.interfaces.IObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class ObserverComponent extends JComponent implements IObserver {
    protected ControlRole controlRole;
    protected int width, height;
    protected BasicStroke stroke;

    public ObserverComponent(int x, int y, int width, int height, ControlRole controlRole) {
        super();
        this.width = width;
        this.height = height;
        this.controlRole = controlRole;
        this.setLocation(x, y);
        this.setSize(width, height);
    }

    abstract void onUpdate();

    @Override
    public void update(ControlRole role) {
        if (this.controlRole != role)
            return;
        onUpdate();
        this.repaint();
    }

    @Override
    public void subscribe(IObservable provider) {
        if (provider != null) {
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    provider.notify(getControlRole());
                }
            });
        }
    }

    public ControlRole getControlRole() {
        return controlRole;
    }
}
