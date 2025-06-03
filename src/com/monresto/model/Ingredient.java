package com.monresto.model;

import com.monresto.patterns.observer.Subject;
import com.monresto.patterns.observer.Observer;
import java.util.ArrayList;
import java.util.List;

public class Ingredient implements Subject {
    private int id;
    private String nom;
    private int quantiteStock;
    private List<Observer> observers;

    public Ingredient(int id, String nom, int quantiteStock) {
        this.id = id;
        this.nom = nom;
        this.quantiteStock = quantiteStock;
        this.observers = new ArrayList<>();
    }

    public int getId() { 
    	return id; 
    }
    public void setId(int id) {
    	this.id = id; 
    }
    public String getNom() { 
    	return nom; 
    }
    public int getQuantiteStock() { 
    	return quantiteStock; 
    }

    public void mettreAJourStock(int delta) {
        this.quantiteStock += delta;
        if (quantiteStock < 5) notifyObservers("Stock bas pour " + nom);
    }

    @Override
    public void attach(Observer o) { 
    	observers.add(o); 
    }
    @Override
    public void detach(Observer o) { 
    	observers.remove(o); 
    }
    @Override
    public void notifyObservers(String message) {
        for (Observer o : observers) o.notifier(message);
    }
}