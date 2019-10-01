package main.lab3.observer;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class NoseTriangle extends JComponent implements IObserver {
    private ControlRole controlRole = ControlRole.NOSE;
    private int width;
    private int height;
    private Color curColor = Color.BLUE;

    public NoseTriangle(int x, int y, int width, int height){
        super();
        this.setLocation(x,y);
        this.width = width;
        this.height = height;
        this.setSize(width,height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setPaint(curColor);
        g2.setStroke(new BasicStroke(2.0f));

        int[] xpoints = {width / 2, 0, width};
        int[] ypoints = {0, height, height};
        g2.fillPolygon(xpoints, ypoints, 3);
    }

    ControlRole getControlRole() {
        return controlRole;
    }

    @Override
    public void update(ControlRole role) {
        if (role != this.getControlRole())
            return;

        this.curColor = getRandomColor();
        this.repaint();
    }

    private Color getRandomColor(){
        int r = getRandomColorPart();
        int g = getRandomColorPart();
        int b = getRandomColorPart();
        return new Color(r,g,b);
    }

    private int getRandomColorPart(){
        return (int) (Math.random()*256);
    }

    @Override
    public void subscribe(IObservable provider) {
        if (provider != null)
            provider.addObserver(this);
    }
}
