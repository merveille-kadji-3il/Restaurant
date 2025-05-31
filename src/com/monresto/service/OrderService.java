package com.monresto.service;

import com.monresto.dao.CommandeDAO;
import com.monresto.model.Commande;
import com.monresto.model.Facture;
import com.monresto.patterns.strategy.PaiementCarte;
import com.monresto.patterns.strategy.PaiementEspeces;
import com.monresto.patterns.strategy.StrategiePaiement;
import java.sql.SQLException;
import java.util.Scanner;

public class OrderService {
    private final CommandeDAO dao;
    private final Scanner scanner = new Scanner(System.in);

    public OrderService() throws SQLException {
        dao = new CommandeDAO();
    }

    public void creerCommande(Commande c) throws Exception {
        dao.create(c);
        System.out.println("Commande créée, id=" + c.getId());
    }

    public void modifierCommande(Commande c) throws Exception {
        dao.update(c);
        System.out.println("Commande modifiée, id=" + c.getId());
    }

    public void annulerCommande(int id) throws Exception {
        dao.delete(id);
        System.out.println("Commande annulée, id=" + id);
    }

    public void finaliserCommande(Commande c) throws Exception {
        double total = c.getContent().stream().mapToDouble(i -> i.getPrix()).sum();
        Facture facture = new Facture(c.getId(), total);
        facture.generer();
        System.out.println("Montant total : " + total + "€. Choisissez le mode de paiement (1=CB,2=Espèces):");
        int choix = scanner.nextInt();
        StrategiePaiement strategie = (choix == 1) ? new PaiementCarte() : new PaiementEspeces();
        strategie.payer(total);
        facture.enregistrerPaiement();
        c.setEtat(c.getEtat());
        dao.update(c);
        System.out.println("Commande finalisée et payée, id=" + c.getId());
    }
}

