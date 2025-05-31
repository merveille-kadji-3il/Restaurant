package com.monresto.model;

public class Cuisinier extends Personnel {
    public Cuisinier(int id, String nom) { 
    	super(id, nom, "cuisinier"); 
    }
    @Override
    public void afficher() { 
    	System.out.println("Cuisinier: " + nom);
    }
}
