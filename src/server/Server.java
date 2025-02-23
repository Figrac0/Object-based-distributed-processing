package server;

import java.io.*;
import java.net.*;
import utils.DistanceCalculator;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server is waiting for connection...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                        InputStream is = clientSocket.getInputStream();
                        OutputStream os = clientSocket.getOutputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                        PrintWriter writer = new PrintWriter(os, true)) {

                    System.out.println("Client connected.");

                    String message;
                    while ((message = reader.readLine()) != null) {
                        String[] coordinates = message.split(",");
                        double x1 = Double.parseDouble(coordinates[0]);
                        double y1 = Double.parseDouble(coordinates[1]);
                        double x2 = Double.parseDouble(coordinates[2]);
                        double y2 = Double.parseDouble(coordinates[3]);

                        double distance = DistanceCalculator.calculateDistance(x1, y1, x2, y2);
                        if (distance == 0) {
                            writer.println("Error: Points are the same.");
                        } else {
                            writer.println("Distance: " + distance);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
