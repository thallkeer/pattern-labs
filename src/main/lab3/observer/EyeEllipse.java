package main.lab3.observer;

import main.lab2.proxy.Commands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class EyeEllipse extends JComponent implements IObserver {
    private int width;
    private int height;
    private ControlRole controlRole;
    private boolean isOpen;

    EyeEllipse(int x, int y, int width, int height, ControlRole role){
        super();
        this.setLocation(x,y);
        this.setSize(width,height);
        this.width = width;
        this.height = height;
        this.controlRole = role;
        isOpen = true;
    }

    private int halfWidth(){
        return width /2;
    }

    private int halfHeight(){
        return height/2;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setPaint(Color.BLUE);
        g2.setStroke(new BasicStroke(2.0f));
        g2.draw(new Ellipse2D.Double(0, 0, width, height));


        g2.setPaint(Color.RED);
        g2.setStroke(new BasicStroke(1f));

        int offset = 15;
        int x = halfWidth() - offset;
        int y = halfHeight() - offset;

        if (isOpen)
            g2.fill(new Ellipse2D.Double(x, y, 30, 30));
        else {
            g2.setStroke(new BasicStroke(3f));
            g2.drawLine(x, halfHeight(), halfWidth() + offset, halfHeight());
        }
    }

    void addClickListener(Gui gui) {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                gui.notify(getControlRole());
            }
        });
    }

    @Override
    public void update(ControlRole role) {
       if (role != getControlRole())
           return;

       this.isOpen = !isOpen;
       this.repaint();
    }

    @Override
    public void subscribe(IObservable provider) {
        if (provider != null)
            provider.addObserver(this);
    }

    private ControlRole getControlRole() {
        return controlRole;
    }
}
