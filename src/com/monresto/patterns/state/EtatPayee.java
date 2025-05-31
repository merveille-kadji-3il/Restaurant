package com.monresto.patterns.state;
import com.monresto.model.Commande;

public class EtatPayee implements EtatCommande {
    @Override
    public void changerEtat(Commande commande) {
        System.out.println("Commande #" + commande.getId() + " est déjà payée.");
    }
}
