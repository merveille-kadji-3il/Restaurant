package com.monresto.model;

import com.monresto.patterns.factory.FactoryMenuItem;

public class Rapport {
    private final String type;

    public Rapport(String type) {
        this.type = type;
    }

    /** Génère un rapport de ventes (CA total) */
    public void genererRapportVentes() {
        // Exemple : parcourir toutes les commandes et calculer le CA
        System.out.println("=== Rapport de ventes ===");
        // TODO : injecter / récupérer OrderService ou DAO pour lister les commandes
    }

    /** Génère un rapport des plats les plus populaires */
    public void genererPlatsPopulaires() {
        System.out.println("=== Plats les plus commandés ===");
        // TODO : analyser la fréquence des MenuItem dans les commandes
    }

    public String getType() {
        return type;
    }
}

