package com.monresto.dao;

import com.monresto.model.Table;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableDAO implements GenericDAO<Table, Integer> {
    private final Connection conn;

    public TableDAO() throws SQLException {
        conn = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void create(Table t) throws SQLException {
        String sql = "INSERT INTO TableRestaurant(numero, capacite, etat) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, t.getNumero());
            stmt.setInt(2, t.getCapacite());
            stmt.setString(3, t.getEtat());
            stmt.executeUpdate();
        }
    }

    @Override
    public Table findById(Integer numero) throws SQLException {
        String sql = "SELECT * FROM TableRestaurant WHERE numero = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numero);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Table t = new Table(rs.getInt("numero"), rs.getInt("capacite"));
                    t.setEtat(rs.getString("etat"));
                    return t;
                }
            }
        }
        return null;
    }

    @Override
    public List<Table> findAll() throws SQLException {
        String sql = "SELECT * FROM TableRestaurant";
        List<Table> list = new ArrayList<>();
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Table t = new Table(rs.getInt("numero"), rs.getInt("capacite"));
                t.setEtat(rs.getString("etat"));
                list.add(t);
            }
        }
        return list;
    }

    @Override
    public void update(Table t) throws SQLException {
        String sql = "UPDATE TableRestaurant SET capacite=?, etat=? WHERE numero=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, t.getCapacite());
            stmt.setString(2, t.getEtat());
            stmt.setInt(3, t.getNumero());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Integer numero) throws SQLException {
        String sql = "DELETE FROM TableRestaurant WHERE numero = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numero);
            stmt.executeUpdate();
        }
    }
}
