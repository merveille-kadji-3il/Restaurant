package com.monresto.model;

public class Boisson extends MenuItem {
    public Boisson(int id, String nom, String description, double prix) {
        super(id, nom, description, prix);
    }

    @Override
    public void afficher() {
        System.out.println("Boisson: " + nom + " - " + prix + "€");
    }
}
