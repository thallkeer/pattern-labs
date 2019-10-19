package main.lab4.mvc;

public class Function {
    public double[] getValues(double[] xPoints){
        double[] yPoints = new double[xPoints.length];
        for (int i = 0; i < xPoints.length; i++) {
            yPoints[i] = getY(xPoints[i]);
        }
        return yPoints;
    }

    public double getY(double x){
        return 2*x;
    }
}
