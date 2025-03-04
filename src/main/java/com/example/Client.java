package com.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {

            System.out.println("Connecting to the RMI registry...");
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            System.out.println("Looking up the remote object...");
            DatabaseService service = (DatabaseService) registry.lookup("DatabaseService");

            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {

                System.out.println("\n1. Add Author");
                System.out.println("2. Add Book");
                System.out.println("3. Print all books with authors");
                System.out.println("4. Delete Author");
                System.out.println("5. Delete Book");
                System.out.println("6. Exit");
                System.out.println("7. Print Authors Table");
                System.out.println("8. Print Books Table");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        // Добавление автора
                        System.out.print("Enter author name: ");
                        String authorName = scanner.nextLine();
                        System.out.print("Enter author country: ");
                        String authorCountry = scanner.nextLine();
                        service.addAuthor(authorName, authorCountry);
                        break;

                    case 2:
                        // Добавление книги
                        System.out.print("Enter book title: ");
                        String bookTitle = scanner.nextLine();
                        System.out.print("Enter author ID: ");
                        int authorId = scanner.nextInt();
                        scanner.nextLine(); // Считываем пустую строку
                        service.addBook(bookTitle, authorId);
                        System.out.println("Book added successfully.");
                        break;

                    case 3:
                        // Вывод всех книг с авторами
                        System.out.println("Printing all books with authors...");
                        service.printAllBooksWithAuthors();
                        break;

                    case 4:
                        // Удаление автора
                        System.out.print("Enter author ID to delete: ");
                        int deleteAuthorId = scanner.nextInt();
                        scanner.nextLine(); // Считываем пустую строку
                        System.out.print("Do you want to delete the author and all associated books? (yes/no): ");
                        String response = scanner.nextLine().trim();
                        boolean deleteBooks = response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y");
                        service.deleteAuthor(deleteAuthorId, deleteBooks);
                        break;

                    case 5:
                        // Удаление книги
                        System.out.print("Enter book ID to delete: ");
                        int deleteBookId = scanner.nextInt();
                        scanner.nextLine();
                        service.deleteBook(deleteBookId);
                        break;

                    case 6:
                        // Выход из программы
                        running = false;
                        System.out.println("Exiting...");
                        break;

                    case 7:
                        // Вывод таблицы авторов
                        System.out.println("Printing Authors Table...");
                        service.printAuthors();
                        break;

                    case 8:
                        // Вывод таблицы книг
                        System.out.println("Printing Books Table...");
                        service.printBooks();
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.err.println("Client exception:");
            e.printStackTrace();
        }
    }
}
