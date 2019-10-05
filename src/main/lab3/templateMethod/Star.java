package main.lab3.templateMethod;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class Star extends BouncingShape {
    int radius = 50;
    double spikiness = 0.5;


    @Override
    public String toString() {
        return "Star";
    }

    int xPoint[] = new int[5];
    int yPoint[] = new int[5];

    @Override
    void draw(Graphics g) {
        int ctrX = radius / 2, ctrY = radius / 2;

        int nPoints = 5 * 2 + 1;

        xPoint = new int[nPoints];
        yPoint = new int[nPoints];

        //generate star
        for (int i = 0; i < nPoints; i++)
        {
            double iRadius = (i % 2 == 0) ? radius : (radius * spikiness);
            double angle = (i * 360.0) / (2*5);

            xPoint[i] = (int) (ctrX + iRadius * Math.cos(Math.toRadians(angle - 90)));
            yPoint[i] = (int) (ctrY + iRadius * Math.sin(Math.toRadians(angle - 90)));
        }

        //paint the star
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.blue);
        g2.fillRect(0, 0, radius, radius);
        g2.setStroke(new BasicStroke(4.f));
        g2.setColor(Color.yellow);
        g2.drawPolyline(xPoint, yPoint, nPoints);

        //insert control lines
        g2.setStroke(new BasicStroke(1.f));
        g2.setColor(Color.black);
        for(int i = 0; i < 5 * 2; i++)
            g2.drawLine(ctrX, ctrY, xPoint[i], yPoint[i]);

        int w1 = radius,
                w2 = (int) (radius * spikiness);
        g2.drawOval(ctrX - w1, ctrY - w1, w1 * 2, w1 * 2);
        g2.drawOval(ctrX - w2, ctrY - w2, w2 * 2, w2 * 2);
    }

    @Override
    void move() {
        if (x + angleX < 0)
            angleX = v;
        else if (x + angleX > boundX - radius)
            angleX = -v;
        else if (y + angleY < 0)
            angleY = v;
        else if (y + angleY > boundY - radius)
            angleY = -v;


        for (int i = 0; i < xPoint.length; i++) {
            xPoint[i] += angleX;
            yPoint[i] += angleY;
        }
    }
}
