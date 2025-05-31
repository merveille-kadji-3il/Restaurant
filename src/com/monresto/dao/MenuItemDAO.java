package com.monresto.dao;

import com.monresto.model.MenuItem;
import com.monresto.patterns.factory.FactoryMenuItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDAO implements GenericDAO<MenuItem, Integer> {
    private final Connection conn;

    public MenuItemDAO() throws SQLException {
        conn = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void create(MenuItem item) throws SQLException {
        String sql = "INSERT INTO MenuItem(nom, description, prix, type) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, item.getNom());
            stmt.setString(2, item.getDescription());
            stmt.setDouble(3, item.getPrix());
            stmt.setString(4, item.getClass().getSimpleName());
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    item.setId(keys.getInt(1));
                }
            }
        }
    }

    @Override
    public MenuItem findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM MenuItem WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return FactoryMenuItem.creerMenuItem(
                        rs.getString("type"),
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getDouble("prix")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<MenuItem> findAll() throws SQLException {
        String sql = "SELECT * FROM MenuItem";
        List<MenuItem> list = new ArrayList<>();
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(FactoryMenuItem.creerMenuItem(
                    rs.getString("type"),
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("description"),
                    rs.getDouble("prix")
                ));
            }
        }
        return list;
    }

    @Override
    public void update(MenuItem item) throws SQLException {
        String sql = "UPDATE MenuItem SET nom=?, description=?, prix=?, type=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getNom());
            stmt.setString(2, item.getDescription());
            stmt.setDouble(3, item.getPrix());
            stmt.setString(4, item.getClass().getSimpleName());
            stmt.setInt(5, item.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM MenuItem WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}