package com.monresto.model;

public class Serveur extends Personnel {
    public Serveur(int id, String nom) { 
    	super(id, nom, "serveur"); 
    }
    
    @Override 
    public void afficher() { 
    	System.out.println("Serveur: " + nom); 
    }
}
