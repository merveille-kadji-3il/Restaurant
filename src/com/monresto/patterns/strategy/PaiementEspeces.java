package com.monresto.patterns.strategy;

public class PaiementEspeces implements StrategiePaiement {
    @Override
    public void payer(double montant) {
        // Logique de paiement espèces (simulée)
        System.out.println("Paiement en espèces de " + montant + "€ reçu.");
    }
}
