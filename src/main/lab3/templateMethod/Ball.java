package main.lab3.templateMethod;

import java.awt.*;

public class Ball extends BouncingShape {

    public Ball(MainPanel.DrawCanvas owner)
    {
        super(owner);
    }

    @Override
    BouncingShape createShape(MainPanel.DrawCanvas owner) {
        return new Ball(owner);
    }

    @Override
    public String toString() {
        return "Ball";
    }

    @Override
    void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, radius / 2, radius / 2);
    }

    @Override
    void move() {
        //TODO: fix copypaste
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
