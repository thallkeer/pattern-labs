package main.lab2.proxy;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Service extends Thread {
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

                if (Commands.MULTIPLY.equals(request)) {
                    double[] numbers = (double[]) in.readObject();
                    out.writeDouble(numbers[0] * numbers[1]);
                    out.flush();
                    running = false;
                }
            }
            in.close();
            out.close();

            client.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
