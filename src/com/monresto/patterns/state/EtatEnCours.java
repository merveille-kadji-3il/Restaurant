package com.monresto.patterns.state;
import com.monresto.model.Commande;

public class EtatEnCours implements EtatCommande {
    @Override
    public void changerEtat(Commande commande) {
        commande.setEtat(new EtatPrete());
        System.out.println("Commande #" + commande.getId() + " passée à l'état 'Prête'.");
    }
}
