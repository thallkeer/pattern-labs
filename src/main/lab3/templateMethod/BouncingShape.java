package main.lab3.templateMethod;

import java.awt.*;

abstract class BouncingShape extends Thread {
    int angleX = 1;
    int angleY = 1;
    int v;
    int x;
    int y;
    int boundX;
    int boundY;
    protected int radius = 100;
    Color color;
    MainPanel.DrawCanvas owner;

    protected BouncingShape(MainPanel.DrawCanvas owner)
    {
        if (owner != null) {
            this.owner = owner;
            Rectangle rect = owner.getBounds();
            x = rect.width-radius/2;
            y = rect.height-radius/2;
            boundX = rect.width;
            boundY = rect.height;
            v = random(5, 10);
            color = new Color(random(255), random(255), random(255));
        }
    }

    private static int random(int maxRange) {
        return (int) Math.round((Math.random() * maxRange));
    }
    private static int random(int min, int max){
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    //TODO: delete unused code
    @Override
    public void run() {
        while (true) {
            move();
            draw(owner.getGraphics());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    abstract BouncingShape createShape(MainPanel.DrawCanvas owner);
    abstract void draw(Graphics g);
    abstract void move();
}
