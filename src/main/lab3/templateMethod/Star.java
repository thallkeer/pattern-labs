package main.lab3.templateMethod;

import java.awt.*;

public class Star extends BouncingShape {
    int radius = 50;
    double spikiness = 0.5;
    int xPoint[];
    int yPoint[];
    int spikes = 5;

    Star(MainPanel.DrawCanvas owner) {
        super(owner);

        int nPoints = spikes * 2 + 1;

        xPoint = new int[nPoints];
        yPoint = new int[nPoints];
        for (int i = 0; i < xPoint.length; i++) {
            double iRadius = (i % 2 == 0) ? radius : (radius * spikiness);
            double angle = (i * 360.0) / (2 * spikes);

            xPoint[i] = (int) (x + iRadius * Math.cos(Math.toRadians(angle - 90)));
            yPoint[i] = (int) (y + iRadius * Math.sin(Math.toRadians(angle - 90)));
        }
    }

    @Override
    BouncingShape createShape(MainPanel.DrawCanvas owner) {
        return new Star(owner);
    }

    @Override
    public String toString() {
        return "Star";
    }

    @Override
    void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4.f));
        g2.setColor(color);
//        for (int i = 0; i < xPoint.length; i++) {
//            double iRadius = (i % 2 == 0) ? radius : (radius * spikiness);
//            double angle = (i * 360.0) / (2 * spikes);
//
//            xPoint[i] = (int) (x + iRadius * Math.cos(Math.toRadians(angle - 90)));
//            yPoint[i] = (int) (y + iRadius * Math.sin(Math.toRadians(angle - 90)));
//        }

        g2.fillPolygon(xPoint, yPoint, xPoint.length);
    }

    @Override
    void move() {
        boolean bounced = true;
//TODO: fix copypaste and delete unused code
        for (int i = 0; i < xPoint.length; i++) {
            bounced = true;
            if (x + angleX - radius < 0)
                angleX = v;
            else if (x + angleX > boundX - radius)
                angleX = -v;
            else if (y + angleY - radius < 0)
                angleY = v;
            else if (y + angleY > boundY - radius)
                angleY = -v;
            else
                bounced = false;

            if (bounced)
                break;
        }
        x += angleX;
        y += angleY;

        for (int i = 0; i < xPoint.length; i++) {
            xPoint[i] += angleX;
            yPoint[i] += angleY;
        }
    }
}
