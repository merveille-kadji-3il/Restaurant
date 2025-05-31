package com.monresto.model;

public class Facture {
    private int commandeId;
    private double montantTotal;
    private boolean payee;

    public Facture(int commandeId, double montantTotal) {
        this.commandeId = commandeId;
        this.montantTotal = montantTotal;
        this.payee = false;
    }

    public void generer() {
        System.out.println("--- Facture #" + commandeId + " ---");
        System.out.println("Total à payer : " + montantTotal + "€");
    }

    public void enregistrerPaiement() {
        this.payee = true;
        System.out.println("Paiement enregistré pour la commande #" + commandeId);
    }
    
    public boolean isPayee() { 
    	return payee; 
    }
 
}