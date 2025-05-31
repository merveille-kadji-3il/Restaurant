package com.monresto.dao;

import com.monresto.model.Reservation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO implements GenericDAO<Reservation, Integer> {
    private final Connection conn;

    public ReservationDAO() throws SQLException {
        conn = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void create(Reservation r) throws SQLException {
        String sql = "INSERT INTO Reservation(client_nom, date_heure, nombre_personnes) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, r.getClientNom());
            stmt.setTimestamp(2, new Timestamp(r.getDateHeure().getTime()));
            stmt.setInt(3, r.getNombrePersonnes());
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) r.setId(keys.getInt(1));
            }
        }
    }

    @Override
    public Reservation findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM Reservation WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Reservation(
                        rs.getInt("id"),
                        rs.getString("client_nom"),
                        rs.getTimestamp("date_heure"),
                        rs.getInt("nombre_personnes")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Reservation> findAll() throws SQLException {
        String sql = "SELECT * FROM Reservation";
        List<Reservation> list = new ArrayList<>();
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Reservation(
                    rs.getInt("id"),
                    rs.getString("client_nom"),
                    rs.getTimestamp("date_heure"),
                    rs.getInt("nombre_personnes")
                ));
            }
        }
        return list;
    }

    @Override
    public void update(Reservation r) throws SQLException {
        String sql = "UPDATE Reservation SET client_nom=?, date_heure=?, nombre_personnes=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, r.getClientNom());
            stmt.setTimestamp(2, new Timestamp(r.getDateHeure().getTime()));
            stmt.setInt(3, r.getNombrePersonnes());
            stmt.setInt(4, r.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Reservation WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}