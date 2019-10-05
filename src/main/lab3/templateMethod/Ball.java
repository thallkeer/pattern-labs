package main.lab3.templateMethod;

import java.awt.*;

public class Ball extends BouncingShape {
    private int radius = 50;

    public Ball()
    {
        super();
        y = y - radius;
    }

    @Override
    public String toString() {
        return "Ball";
    }

    @Override
    void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x,y,radius,radius);
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

        x += angleX;
        y += angleY;
    }

}
