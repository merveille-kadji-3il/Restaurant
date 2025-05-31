package com.monresto.patterns.state;
import com.monresto.model.Commande;

public class EtatNouvelle implements EtatCommande {
    @Override
    public void changerEtat(Commande commande) {
        commande.setEtat(new EtatEnCours());
        System.out.println("Commande #" + commande.getId() + " passée à l'état 'En cours'.");
    }
}
