package com.monresto.model;

public class Dessert extends MenuItem {
    public Dessert(int id, String nom, String description, double prix) {
        super(id, nom, description, prix);
    }

    @Override
    public void afficher() {
        System.out.println("Dessert: " + nom + " - " + prix + "€");
    }
}
