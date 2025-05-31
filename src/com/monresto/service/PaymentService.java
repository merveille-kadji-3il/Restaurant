package com.monresto.service;

import com.monresto.patterns.strategy.StrategiePaiement;

public class PaymentService {
    public void effectuerPaiement(StrategiePaiement strategie, double montant) {
        strategie.payer(montant);
        System.out.println("Paiement effectué de " + montant + "€");
    }
}
