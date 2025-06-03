package com.monresto.patterns.strategy;

public class PaiementCarte implements StrategiePaiement {

	@Override
    public void payer(double montant) {
        // simulation
        System.out.println("Paiement par carte bancaire de " + montant + "€ en cours...");
        System.out.println("Transaction CB approuvée.");
    }
	
}
