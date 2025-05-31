package com.monresto.model;

public class Manager extends Personnel {
    public Manager(int id, String nom) { 
    	super(id, nom, "manager");
    }
    @Override
    public void afficher() { 
    	System.out.println("Manager: " + nom);
    }
}
