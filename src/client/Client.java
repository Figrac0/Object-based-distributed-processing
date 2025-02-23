package client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Socket socket = new Socket("localhost", 12345);
                OutputStream os = socket.getOutputStream();
                InputStream is = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                PrintWriter writer = new PrintWriter(os, true)) {

            System.out.println("Connected to server.");

            while (true) {
                System.out.print("Enter coordinates of first point (x1 y1): ");
                double x1 = scanner.nextDouble();
                double y1 = scanner.nextDouble();

                System.out.print("Enter coordinates of second point (x2 y2): ");
                double x2 = scanner.nextDouble();
                double y2 = scanner.nextDouble();

                String message = x1 + "," + y1 + "," + x2 + "," + y2;
                writer.println(message); // Send data to the server

                String response = reader.readLine(); // Read the response from the server
                System.out.println("Server response: " + response);

                if (response.equals("exit")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
