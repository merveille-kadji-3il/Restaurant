package com.monresto.model;

public class Plat extends MenuItem {
    public Plat(int id, String nom, String description, double prix) {
        super(id, nom, description, prix);
    }

    @Override
    public void afficher() {
        System.out.println("Plat: " + nom + " - " + prix + "€");
    }
}
