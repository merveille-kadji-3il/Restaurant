package com.monresto.patterns.factory;

import com.monresto.model.Rapport;

public class FactoryRapport {
    
    public static Rapport creerRapport(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Le type de rapport ne peut pas être null");
        }
        switch (type.toLowerCase()) {
            case "ventes":
                return new Rapport("ventes");
            case "populaires":
                return new Rapport("populaires");
            default:
                throw new IllegalArgumentException("Type de rapport inconnu : " + type);
        }
    }
}
