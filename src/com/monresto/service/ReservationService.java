package com.monresto.service;

import com.monresto.dao.ReservationDAO;
import com.monresto.model.Reservation;
import java.sql.SQLException;
import java.util.List;

public class ReservationService {
    private final ReservationDAO dao;

    public ReservationService() throws SQLException {
        dao = new ReservationDAO();
    }

    public void creerReservation(Reservation r) throws Exception {
        dao.create(r);
        System.out.println("Réservation créée : id=" + r.getId());
    }

    public void modifierReservation(Reservation r) throws Exception {
        dao.update(r);
        System.out.println("Réservation modifiée : id=" + r.getId());
    }

    public void annulerReservation(int id) throws Exception {
        dao.delete(id);
        System.out.println("Réservation annulée : id=" + id);
    }

    public List<Reservation> listerReservations() throws Exception {
        return dao.findAll();
    }
    
    public Reservation trouverReservation(int id) throws Exception {
        return dao.findById(id);
    }
}
