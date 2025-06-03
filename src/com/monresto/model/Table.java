package com.monresto.model;

public class Table {
    private int numero;
    private int capacite;
    private String etat; 

    public Table(int numero, int capacite) {
        this.numero = numero;
        this.capacite = capacite;
        this.etat = "libre";
    }

    public int getNumero() { 
    	return numero; 
    }
    public int getCapacite() { 
    	return capacite; 
    }
    public String getEtat() { 
    	return etat; 
    }
    public void setEtat(String etat) { 
    	this.etat = etat; 
    }
}
