package com.monresto.model;

import java.util.Date;

public class Reservation {
    private int id;
    private String clientNom;
    private Date dateHeure;
    private int nombrePersonnes;

    public Reservation(int id, String clientNom, Date dateHeure, int nombrePersonnes) {
        this.id = id;
        this.clientNom = clientNom;
        this.dateHeure = dateHeure;
        this.nombrePersonnes = nombrePersonnes;
    }

    public int getId() { 
    	return id; 
    }
    public void setId(int id) { 
    	this.id = id; 
    }
    public String getClientNom() { 
    	return clientNom; 
    }
    public Date getDateHeure() { 
    	return dateHeure; 
    }
    public int getNombrePersonnes() { 
    	return nombrePersonnes; 
    }
}
