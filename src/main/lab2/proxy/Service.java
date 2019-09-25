package main.lab2.proxy;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Service extends Thread implements IMultiplier {
    private static final int PORT = 5000;
    private boolean running = true;

    public Service() {
        this.start();
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            Socket client = serverSocket.accept();
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

            while (running) {
                String request = in.readUTF();

                    switch (request) {
                        case Commands.MULTIPLY:
                            double[] numbers = (double[]) in.readObject();
                            out.writeDouble(multiply(numbers[0], numbers[1]));
                            out.flush();
                            running = false;
                            break;
                    }
            }
            in.close();
            out.close();

            client.close();
        }
        catch (IOException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public double multiply(double a, double b) {
        return a * b;
    }
}
