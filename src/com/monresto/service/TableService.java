package com.monresto.service;

import com.monresto.dao.TableDAO;
import com.monresto.model.Table;
import java.sql.SQLException;
import java.util.List;

public class TableService {
    private final TableDAO dao;

    public TableService() throws SQLException {
        this.dao = new TableDAO();
    }

    public void ajouterTable(Table t) throws Exception {
        dao.create(t);
        System.out.println("Table ajoutée : numéro=" + t.getNumero());
    }

    public List<Table> listerTables() throws Exception {
        return dao.findAll();
    }

    public Table trouverTable(int numero) throws Exception {
        return dao.findById(numero);
    }

    public void modifierTable(Table t) throws Exception {
        dao.update(t);
        System.out.println("Table modifiée : numéro=" + t.getNumero());
    }

    public void supprimerTable(int numero) throws Exception {
        dao.delete(numero);
        System.out.println("Table supprimée : numéro=" + numero);
    }
}