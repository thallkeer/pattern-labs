package main.lab3.templateMethod;

import javax.swing.*;
import java.awt.*;

public class Square extends BouncingShape {

    public Square(MainPanel.DrawCanvas owner){
        super(owner);
    }

    @Override
    BouncingShape createShape(MainPanel.DrawCanvas owner) {
        return new Square(owner);
    }

    @Override
    public String toString() {
        return "Square";
    }

    @Override
    void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x,y,radius/2,radius/2);
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
