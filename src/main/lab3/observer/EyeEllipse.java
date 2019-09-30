package main.lab3.observer;

import main.lab2.proxy.Commands;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class EyeEllipse extends JComponent implements IObserver {
    int x,y, width,height;
    private ControlRole controlRole;


    public EyeEllipse(int x, int y, int width, int height){
        super();
        this.setLocation(x,y);
        this.setSize(width,height);
        this.setBackground(Color.RED);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setPaint(Color.BLUE);
        g2.setStroke(new BasicStroke(2.0f));
        g2.draw(new Ellipse2D.Double(0,0, width,height));

        g2.setPaint(Color.RED);
        g2.setStroke(new BasicStroke(1f));
        g2.fill(new Ellipse2D.Double(width/2-15,height/2-15,30,30));
    }

    @Override
    public void update(ControlRole role) {
        System.out.println("Hi from " + role);
    }

    @Override
    public void subscribe(IObservable provider) {
        if (provider != null)
            provider.addObserver(this);
    }

    public ControlRole getControlRole() {
        return controlRole;
    }

    public void setControlRole(ControlRole controlRole) {
        this.controlRole = controlRole;
    }
}
