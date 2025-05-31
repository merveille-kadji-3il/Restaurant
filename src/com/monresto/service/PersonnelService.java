package com.monresto.service;

import com.monresto.dao.PersonnelDAO;
import com.monresto.model.Personnel;
import java.sql.SQLException;
import java.util.List;

public class PersonnelService {
    private final PersonnelDAO dao;

    public PersonnelService() throws SQLException {
        this.dao = new PersonnelDAO();
    }

    public void ajouterPersonnel(Personnel p) throws Exception {
        dao.create(p);
        System.out.println("Personnel ajouté : " + p.getNom());
    }

    public List<Personnel> listerPersonnel() throws Exception {
        return dao.findAll();
    }

    public void modifierPersonnel(Personnel p) throws Exception {
        dao.update(p);
        System.out.println("Personnel modifié : id=" + p.getId());
    }

    public void supprimerPersonnel(int id) throws Exception {
        dao.delete(id);
        System.out.println("Personnel supprimé : id=" + id);
    }
}

