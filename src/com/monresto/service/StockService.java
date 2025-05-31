package com.monresto.service;

import com.monresto.dao.IngredientDAO;
import com.monresto.model.Ingredient;
import java.sql.SQLException;
import java.util.List;

public class StockService {
    private final IngredientDAO dao;

    public StockService() throws SQLException {
        this.dao = new IngredientDAO();
    }

    public void ajouterIngredient(Ingredient i) throws Exception {
        dao.create(i);
        System.out.println("Ingrédient ajouté : " + i.getNom());
    }

    public List<Ingredient> listerIngredients() throws Exception {
        return dao.findAll();
    }

    public void mettreAJourStock(int id, int delta) throws Exception {
        Ingredient i = dao.findById(id);
        i.mettreAJourStock(delta);
        dao.update(i);
        System.out.println("Stock mis à jour pour id=" + id + " delta=" + delta);
    }

    public void supprimerIngredient(int id) throws Exception {
        dao.delete(id);
        System.out.println("Ingrédient supprimé : id=" + id);
    }
}
