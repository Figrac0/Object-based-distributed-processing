package com.example;

import java.sql.*;

public class DatabaseHandler {
    private Connection connection;

    public DatabaseHandler() {
        try {
            // Регистрируем драйвер SQLite
            System.out.println("Loading SQLite JDBC driver...");
            Class.forName("org.sqlite.JDBC");
            System.out.println("SQLite JDBC driver loaded!");

            // Устанавливаем соединение с базой данных
            System.out.println("Connecting to the database...");
            connection = DriverManager.getConnection("jdbc:sqlite:lb2.sqlite");
            System.out.println("Connected to the database!");
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database!");
            e.printStackTrace();
        }
    }

    // Метод для проверки существования автора
    private boolean authorExists(int authorId) {
        String query = "SELECT id FROM Authors WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, authorId);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void printAllBooksWithAuthors() {
        String query = "SELECT Books.id, Books.title, Authors.name, Authors.country " +
                "FROM Books " +
                "LEFT JOIN Authors ON Books.author_id = Authors.id";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            boolean hasBooks = false;
            while (rs.next()) {
                int bookId = rs.getInt("id");
                String title = rs.getString("title");
                String authorName = rs.getString("name");
                String country = rs.getString("country");

                hasBooks = true;
                System.out.printf("Book ID: %d, Title: %s, Author: %s, Country: %s\n",
                        bookId, title, authorName, country);
            }
            if (!hasBooks) {
                System.out.println("No books available.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAuthor(String name, String country) {
        String query = "INSERT INTO Authors (name, country) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.setString(2, country);
            int rowsAffected = pstmt.executeUpdate();

            // Получаем сгенерированный ID
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int authorId = generatedKeys.getInt(1);
                        System.out.println(
                                "Author added successfully: " + name + ", " + country + " (ID: " + authorId + ")");
                    } else {
                        System.out.println("Failed to retrieve the author ID.");
                    }
                }
            } else {
                System.out.println("Failed to add author.");
            }
        } catch (SQLException e) {
            System.err.println("Error while adding author: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addBook(String title, int authorId) {
        // Проверка существования автора
        if (!authorExists(authorId)) {
            System.out.println("Author with ID " + authorId + " does not exist.");
            return;
        }

        String query = "INSERT INTO Books (title, author_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, title);
            pstmt.setInt(2, authorId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book added successfully: " + title + " by author with ID " + authorId);
            } else {
                System.out.println("Failed to add book.");
            }
        } catch (SQLException e) {
            System.err.println("Error while adding book: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteAuthor(int authorId, boolean deleteBooks) {
        String checkQuery = "SELECT COUNT(*) FROM Books WHERE author_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(checkQuery)) {
            pstmt.setInt(1, authorId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int bookCount = rs.getInt(1);

                if (bookCount > 0) {
                    if (deleteBooks) {
                        // Удаляем все книги, связанные с автором
                        String deleteBooksQuery = "DELETE FROM Books WHERE author_id = ?";
                        try (PreparedStatement deleteBooksStmt = connection.prepareStatement(deleteBooksQuery)) {
                            deleteBooksStmt.setInt(1, authorId);
                            int deletedBooks = deleteBooksStmt.executeUpdate();
                            if (deletedBooks > 0) {
                                System.out.println("Deleted " + deletedBooks + " books associated with the author.");
                            }
                        }

                        // Теперь удаляем автора
                        String deleteAuthorQuery = "DELETE FROM Authors WHERE id = ?";
                        try (PreparedStatement deleteAuthorStmt = connection.prepareStatement(deleteAuthorQuery)) {
                            deleteAuthorStmt.setInt(1, authorId);
                            int deletedAuthors = deleteAuthorStmt.executeUpdate();
                            if (deletedAuthors > 0) {
                                System.out.println("Author deleted successfully.");
                            } else {
                                System.out.println("Author not found or already deleted.");
                            }
                        }
                    } else {
                        // Если пользователь отказался удалять книги, отменяем удаление автора
                        System.out.println("Deletion aborted. Author was not deleted.");
                    }
                } else {
                    // Если книг нет, просто удаляем автора
                    String deleteAuthorQuery = "DELETE FROM Authors WHERE id = ?";
                    try (PreparedStatement deleteAuthorStmt = connection.prepareStatement(deleteAuthorQuery)) {
                        deleteAuthorStmt.setInt(1, authorId);
                        int deletedAuthors = deleteAuthorStmt.executeUpdate();
                        if (deletedAuthors > 0) {
                            System.out.println("Author deleted successfully.");
                        } else {
                            System.out.println("Author not found or already deleted.");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int bookId) {
        String query = "DELETE FROM Books WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, bookId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book deleted successfully.");
            } else {
                System.out.println("No book found with the provided ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printAuthors() {
        String query = "SELECT * FROM Authors";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            boolean hasAuthors = false;
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String country = rs.getString("country");
                System.out.printf("Author ID: %d, Name: %s, Country: %s\n", id, name, country);
                hasAuthors = true;
            }
            if (!hasAuthors) {
                System.out.println("No authors available.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printBooks() {
        String query = "SELECT * FROM Books";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            boolean hasBooks = false;
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int authorId = rs.getInt("author_id");
                System.out.printf("Book ID: %d, Title: %s, Author ID: %d\n", id, title, authorId);
                hasBooks = true;
            }
            if (!hasBooks) {
                System.out.println("No books available.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
