package com.monresto.dao;

import com.monresto.model.Commande;
import com.monresto.model.MenuItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAO implements GenericDAO<Commande, Integer> {
    private final Connection conn;

    public CommandeDAO() throws SQLException {
        conn = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void create(Commande cmd) throws SQLException {
        String sql = "INSERT INTO Commande(date_commande, etat) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setTimestamp(1, new Timestamp(cmd.getDate().getTime()));
            stmt.setString(2, cmd.getEtat().getClass().getSimpleName());
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) cmd.setId(keys.getInt(1));
            }
        }
        String link = "INSERT INTO Commande_MenuItem(commande_id, menuitem_id, quantite) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(link)) {
            for (MenuItem item : cmd.getContent()) {
                stmt.setInt(1, cmd.getId());
                stmt.setInt(2, item.getId());
                stmt.setInt(3, 1);
                stmt.executeUpdate();
            }
        }
    }

    @Override
    public Commande findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM Commande WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Commande cmd = new Commande(rs.getInt("id"));
                    cmd.setDate(new java.util.Date(rs.getTimestamp("date_commande").getTime()));
                    return cmd;
                }
            }
        }
        return null;
    }

    @Override
    public List<Commande> findAll() throws SQLException {
        String sql = "SELECT * FROM Commande";
        List<Commande> list = new ArrayList<>();
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Commande cmd = new Commande(rs.getInt("id"));
                cmd.setDate(new java.util.Date(rs.getTimestamp("date_commande").getTime()));
                list.add(cmd);
            }
        }
        return list;
    }

    @Override
    public void update(Commande cmd) throws SQLException {
        String sql = "UPDATE Commande SET etat = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cmd.getEtat().getClass().getSimpleName());
            stmt.setInt(2, cmd.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Commande WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
