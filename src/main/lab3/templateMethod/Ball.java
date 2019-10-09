package main.lab3.templateMethod;

import java.awt.*;

public class Ball extends BouncingShape {

    public Ball() { }

    public Ball(MainPanel.DrawCanvas owner) {
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
    void paintShape(Graphics2D g) {
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    @Override
    void rotate(boolean bounced) {

    }
}
