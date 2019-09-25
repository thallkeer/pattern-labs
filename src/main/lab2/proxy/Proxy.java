package main.lab2.proxy;

import java.io.*;
import java.net.Socket;

public class Proxy implements IMultiplier {
    static final int PORT = 5000;

    @Override
    public double multiply(double a, double b) {
        double result = 0;
        try {
            try (Socket socket = new Socket("localhost", PORT);
                 ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

                oos.writeUTF(Commands.MULTIPLY);
                oos.flush();

                double[] arrayToSend = new double[2];
                arrayToSend[0] = a;
                arrayToSend[1] = b;
                oos.writeObject(arrayToSend);
                oos.flush();

                result = ois.readDouble();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
