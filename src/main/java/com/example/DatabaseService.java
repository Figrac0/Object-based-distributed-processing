package com.example;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DatabaseService extends Remote {
    void printAllBooksWithAuthors() throws RemoteException;

    void addAuthor(String name, String country) throws RemoteException;

    void addBook(String title, int authorId) throws RemoteException;

    void deleteAuthor(int authorId, boolean deleteBooks) throws RemoteException;

    void deleteBook(int bookId) throws RemoteException;

    void printAuthors() throws RemoteException;

    void printBooks() throws RemoteException;

}