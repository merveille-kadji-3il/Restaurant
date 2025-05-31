package com.monresto.model;

import com.monresto.patterns.state.EtatCommande;
import com.monresto.patterns.state.EtatNouvelle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Commande {
    private int id;
    private Date date;
    private EtatCommande etat;
    private List<MenuItem> content;

    public Commande(int id) {
        this.id = id;
        this.date = new Date();
        this.etat = new EtatNouvelle();
        this.content = new ArrayList<>();
    }

    public int getId() { 
    	return id; 
    }
    public void setId(int id) { 
    	this.id = id; 
    	
    }
    public Date getDate() { 
    	return date; 
    }
    public void setDate(Date date) { 
    	this.date = date; 
    }
    public EtatCommande getEtat() { 
    	return etat; 
    	
    }
    public void setEtat(EtatCommande etat) { 
    	this.etat = etat; 
    	
    }
    public List<MenuItem> getContent() { 
    	return content; 
    	
    }

    public void notifierEtat() {
        etat.changerEtat(this);
    }
}
