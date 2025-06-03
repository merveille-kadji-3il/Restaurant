package com.monresto.patterns.strategy;

public class PaiementEspeces implements StrategiePaiement {
    @Override
    public void payer(double montant) {
        // simulation
        System.out.println("Paiement en espèces de " + montant + "€ reçu.");
    }
}
