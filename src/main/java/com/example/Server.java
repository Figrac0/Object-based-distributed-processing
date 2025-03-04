package com.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            DatabaseService service = new DatabaseServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            System.out.println("RMI registry created...");

            registry.rebind("DatabaseService", service);
            System.out.println("Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}