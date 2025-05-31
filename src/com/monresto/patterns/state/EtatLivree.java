package com.monresto.patterns.state;
import com.monresto.model.Commande;

public class EtatLivree implements EtatCommande {
    @Override
    public void changerEtat(Commande commande) {
        commande.setEtat(new EtatPayee());
        System.out.println("Commande #" + commande.getId() + " passée à l'état 'Payée'.");
    }
}
