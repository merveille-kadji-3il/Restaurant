package com.monresto.dao;

import com.monresto.model.Ingredient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAO implements GenericDAO<Ingredient, Integer> {
    private final Connection conn;

    public IngredientDAO() throws SQLException {
        conn = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void create(Ingredient i) throws SQLException {
        String sql = "INSERT INTO Ingredient(nom, quantite_stock) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, i.getNom());
            stmt.setInt(2, i.getQuantiteStock());
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) i.setId(keys.getInt(1));
            }
        }
    }

    @Override
    public Ingredient findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM Ingredient WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Ingredient(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getInt("quantite_stock")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Ingredient> findAll() throws SQLException {
        String sql = "SELECT * FROM Ingredient";
        List<Ingredient> list = new ArrayList<>();
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Ingredient(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getInt("quantite_stock")
                ));
            }
        }
        return list;
    }

    @Override
    public void update(Ingredient i) throws SQLException {
        String sql = "UPDATE Ingredient SET nom=?, quantite_stock=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, i.getNom());
            stmt.setInt(2, i.getQuantiteStock());
            stmt.setInt(3, i.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Ingredient WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
