package com.example.demo;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BD {
    public BD() {
    }

    public static Books Select_book(String code) throws ClassNotFoundException, SQLException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/library";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String consult_SQL = "SELECT * FROM books WHERE code = ?";

        PreparedStatement statement = connection.prepareStatement(consult_SQL);
        statement.setString(1, code); // Establecer el valor del parámetro

        // Ejecutar la consulta
        ResultSet resultSet2 = statement.executeQuery();

        // Procesar el resultado si existe
        if (resultSet2.next()) {

            code = resultSet2.getString("code");
            String name = resultSet2.getString("name");
            String editorial = resultSet2.getString("editorial");
            String code_author = resultSet2.getString("code_author");
            String reference = resultSet2.getString("reference");

            Books books = new Books(code, name, editorial, code_author, reference);

            return books;
        }
        // Cerrar recursos
        resultSet2.close();
        statement.close();
        connection.close();

        return null;
    }

    public static int Delete(String code) throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/library";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        String sentenciaSQL = "DELETE FROM books WHERE code = ?";
        PreparedStatement prepareStatement = connection2.prepareStatement(sentenciaSQL);
        prepareStatement.setString(1, code);
        int f = prepareStatement.executeUpdate();

        if (f > 0) {
            System.out.println("Libro eliminado de manera exitosa.");
        } else {
            System.out.println(Errors.error_delete);
        }
        return f;
    }

    public static void Delete_all() throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/library";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        String sentenciaSQL = "DELETE FROM books";
        PreparedStatement prepareStatement = connection2.prepareStatement(sentenciaSQL);

        int f = prepareStatement.executeUpdate();

        System.out.println("Libros eliminados correctamente");

    }


    public String Select_editorial(String editorial) throws ClassNotFoundException, SQLException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/library";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String consult_SQL = "SELECT * FROM authors WHERE worked_editorial = ?";

        PreparedStatement statement = connection.prepareStatement(consult_SQL);
        statement.setString(1, editorial);

        // Ejecutar la consulta
        ResultSet resultSet2 = statement.executeQuery();

        // Procesar el resultado si existe
        if (resultSet2.next()) {

            String worked_editorial = resultSet2.getString("worked_editorial");

            return worked_editorial;
        }
        // Cerrar recursos
        resultSet2.close();
        statement.close();
        connection.close();

        return "";
    }

    public String Select_nit(String code_author) throws ClassNotFoundException, SQLException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/library";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String consult_SQL = "SELECT * FROM authors WHERE nit = ?";

        PreparedStatement statement = connection.prepareStatement(consult_SQL);
        statement.setString(1, code_author);

        // Ejecutar la consulta
        ResultSet resultSet2 = statement.executeQuery();

        // Procesar el resultado si existe
        if (resultSet2.next()) {

            String nit = resultSet2.getString("nit");

            return code_author;
        }
        // Cerrar recursos
        resultSet2.close();
        statement.close();
        connection.close();

        return "";

    }

    public Books Register(String code, String name, String editorial, String code_author, String reference) {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/library";
        String username = "root";
        String password = "";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books");

            // Sentencia INSERT
            String sql = "INSERT INTO books (code , name, editorial, code_author, reference) VALUES (?, ?, ?, ?, ?)";

            // Preparar la sentencia
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, code);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, editorial);
            preparedStatement.setString(4, code_author);
            preparedStatement.setString(5, reference);

            // Ejecutar la sentencia
            int files = preparedStatement.executeUpdate();

            if (files > 0) {
                System.out.println("Libro registrado de manera exitosa.");
                return new Books(code, name, editorial, code_author, reference);
            } else {
                System.out.println(Errors.error_register);
            }

            preparedStatement.close();
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Books(null, null, null, null, null);
    }

    public Books Edit(String code, String name, String editorial, String code_author, String reference) throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/library";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        String consult = "UPDATE books SET name = ?, editorial = ?, code_author = ?, reference = ? WHERE code = ?";

        PreparedStatement preparedStatement = connection2.prepareStatement(consult);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, editorial);
        preparedStatement.setString(3, code_author);
        preparedStatement.setString(4, reference);
        preparedStatement.setString(5, code);

        int files = preparedStatement.executeUpdate();
        if (files > 0) {
            System.out.println("Libro actualizado de manera exitosa");
            return new Books(code, name, editorial, code_author, reference);
        } else {
            System.out.println(Errors.error_update);
        }
        preparedStatement.close();
        connection2.close();
        return new Books(null, null, null, null, null);
    }

    public List<Books> Search_all() throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/library";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM books");

        List<Books> list = new ArrayList<>();

        while (resultSet2.next()) {
            String code = resultSet2.getString("code");
            String name = resultSet2.getString("name");
            String editorial = resultSet2.getString("editorial");
            String code_author = resultSet2.getString("code_author");
            String reference = resultSet2.getString("reference");

            Books books = new Books(code, name, editorial, code_author, reference);
            list.add(books);
        }

        return list;
    }
}



