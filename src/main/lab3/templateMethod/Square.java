package main.lab3.templateMethod;

import java.awt.*;

public class Square extends BouncingShape {
    private int a = 50;


    @Override
    public String toString() {
        return "Square";
    }

    @Override
    void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x,y,a,a);
    }

    @Override
    void move() {
        if (x + angleX < 0)
            angleX = v;
        else if (x + angleX > boundX - a)
            angleX = -v;
        else if (y + angleY < 0)
            angleY = v;
        else if (y + angleY > boundY - a)
            angleY = -v;

        x += angleX;
        y += angleY;
    }
}
