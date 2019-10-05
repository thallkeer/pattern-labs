package main.lab3.templateMethod;

import java.awt.*;

abstract class BouncingShape {
    int angleX = 1;
    int angleY = 1;
    int v;
    int x = 600;
    int y = 525;
    int boundX = 600;
    int boundY = 525;
    Color color;

    BouncingShape()
    {
        v = random(5,10);
        color = new Color(random(255),random(255),random(255));
    }

    public static int random(int maxRange) {
        return (int) Math.round((Math.random() * maxRange));
    }

    public static int random(int min, int max){
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    abstract void draw(Graphics g);
    abstract void move();
}
