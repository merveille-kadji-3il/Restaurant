package com.monresto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    private static final String URL = "jdbc:postgresql://localhost:5432/restaurant";
    private static final String USER = "postgres";
    private static final String PASSWORD = "laeticia";

    private DatabaseConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver"); // Charge le driver PostgreSQL
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur : Driver PostgreSQL non trouvé.");
            e.printStackTrace();
        }
    }

    public static synchronized DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
