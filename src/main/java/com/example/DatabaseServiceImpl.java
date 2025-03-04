package com.example;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DatabaseServiceImpl extends UnicastRemoteObject implements DatabaseService {
    private DatabaseHandler dbHandler;

    public DatabaseServiceImpl() throws RemoteException {
        super();
        dbHandler = new DatabaseHandler();
    }

    @Override
    public void printAllBooksWithAuthors() throws RemoteException {
        dbHandler.printAllBooksWithAuthors();
    }

    @Override
    public void addAuthor(String name, String country) throws RemoteException {
        dbHandler.addAuthor(name, country);
    }

    @Override
    public void addBook(String title, int authorId) throws RemoteException {
        dbHandler.addBook(title, authorId);
    }

    @Override
    public void deleteAuthor(int authorId, boolean deleteBooks) throws RemoteException {
        dbHandler.deleteAuthor(authorId, deleteBooks);
    }

    @Override
    public void deleteBook(int bookId) throws RemoteException {
        dbHandler.deleteBook(bookId);
    }

    @Override
    public void printAuthors() throws RemoteException {
        dbHandler.printAuthors();
    }

    @Override
    public void printBooks() throws RemoteException {
        dbHandler.printBooks();
    }
}