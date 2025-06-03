package com.monresto.model;

import com.monresto.patterns.factory.FactoryMenuItem;

public class Rapport {
    private final String type;

    public Rapport(String type) {
        this.type = type;
    }

    public void genererRapportVentes() {
       
        System.out.println("=== Rapport de ventes ===");
        
    }

    public void genererPlatsPopulaires() {
        System.out.println("=== Plats les plus commandés ===");
    
    }

    public String getType() {
        return type;
    }
}

