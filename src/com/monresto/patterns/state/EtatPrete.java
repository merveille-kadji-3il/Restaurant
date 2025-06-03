package com.monresto.patterns.state;
import com.monresto.model.Commande;

public class EtatPrete implements EtatCommande {
    @Override
    public void changerEtat(Commande commande) {
        commande.setEtat(new EtatLivree());
        System.out.println("Commande :" + commande.getId() + " passée à l'état 'Livrée'.");
    }
}
