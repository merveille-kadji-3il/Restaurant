package com.monresto.service;

import com.monresto.dao.MenuItemDAO;
import com.monresto.model.MenuItem;
import java.sql.SQLException;
import java.util.List;

public class MenuService {
    private final MenuItemDAO dao;

    public MenuService() throws SQLException {
        this.dao = new MenuItemDAO();
    }

    public void ajouterMenuItem(MenuItem item) throws Exception {
        dao.create(item);
        System.out.println("MenuItem ajouté : " + item.getNom());
    }

    public List<MenuItem> listerMenuItems() throws Exception {
        return dao.findAll();
    }

    public MenuItem findById(int id) throws Exception {
        return dao.findById(id);
    }

    public void modifierMenuItem(MenuItem item) throws Exception {
        dao.update(item);
        System.out.println("MenuItem modifié : id=" + item.getId());
    }

    public void supprimerMenuItem(int id) throws Exception {
        dao.delete(id);
        System.out.println("MenuItem supprimé : id=" + id);
    }
}
