package com.monresto.dao;

import com.monresto.model.Personnel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonnelDAO implements GenericDAO<Personnel, Integer> {
    private final Connection conn;

    public PersonnelDAO() throws SQLException {
        conn = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void create(Personnel p) throws SQLException {
        String sql = "INSERT INTO Personnel(nom, role) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, p.getNom());
            stmt.setString(2, p.getRole());
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) p.setId(keys.getInt(1));
            }
        }
    }

    @Override
    public Personnel findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM Personnel WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String role = rs.getString("role");
                    return Personnel.createByRole(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        role
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Personnel> findAll() throws SQLException {
        String sql = "SELECT * FROM Personnel";
        List<Personnel> list = new ArrayList<>();
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(Personnel.createByRole(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("role")
                ));
            }
        }
        return list;
    }

    @Override
    public void update(Personnel p) throws SQLException {
        String sql = "UPDATE Personnel SET nom=?, role=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNom());
            stmt.setString(2, p.getRole());
            stmt.setInt(3, p.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Personnel WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
