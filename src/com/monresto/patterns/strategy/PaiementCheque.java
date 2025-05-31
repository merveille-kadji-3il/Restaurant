package com.monresto.patterns.strategy;

public class PaiementCheque implements StrategiePaiement {
    @Override
    public void payer(double montant) {
        // Logique de paiement par chèque (simulée)
        System.out.println("Paiement par chèque de " + montant + "€ reçu, vérification du chèque en cours...");
        System.out.println("Chèque validé et encaissé.");
    }
}
