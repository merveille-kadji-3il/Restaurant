package com.monresto.patterns.state;
import com.monresto.model.Commande;

public interface EtatCommande {
	void changerEtat(Commande c);
}
