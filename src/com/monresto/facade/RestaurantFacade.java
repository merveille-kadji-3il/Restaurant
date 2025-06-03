package com.monresto.facade;

import com.monresto.model.MenuItem;
import com.monresto.model.Commande;
import com.monresto.model.Reservation;
import com.monresto.model.Rapport;
import com.monresto.patterns.factory.FactoryMenuItem;
import com.monresto.patterns.factory.FactoryRapport;
import com.monresto.service.MenuService;
import com.monresto.service.ReservationService;
import com.monresto.service.OrderService;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class RestaurantFacade {
    private final MenuService menuService;
    private final ReservationService reservationService;
    private final OrderService orderService;
    private final Scanner scanner;

    public RestaurantFacade() throws SQLException {
        this.menuService = new MenuService();
        this.reservationService = new ReservationService();
        this.orderService = new OrderService();
        this.scanner = new Scanner(System.in);
    }

    public void demarrer() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n---- Restaurant Management ----");
            System.out.println("1. Gestion du menu");
            System.out.println("2. Gestion des réservations");
            System.out.println("3. Gestion des commandes");
            System.out.println("4. Générer rapport");
            System.out.println("5. Quitter");
            System.out.print("Choix: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1: menuManagement(); 
                	break;
                case 2: reservationManagement(); 
                	break;
                case 3: commandeManagement(); 
                	break;
                case 4: rapportManagement(); 
                	break;
                case 5: exit = true; 
                	break;
                default: System.out.println("Choix invalide.");
            }
        }
        System.out.println("Merci pour votre visite !");
    }

    private void menuManagement() {
        try {
            System.out.println("\n-- Gestion du menu --");
            System.out.println("1. Ajouter un élément");
            System.out.println("2. Lister les éléments");
            System.out.println("3. Supprimer un élément");
            System.out.print("Choix: ");
            int c = Integer.parseInt(scanner.nextLine());
            switch (c) {
                case 1:
                    System.out.print("Type (plat/boisson/dessert): ");
                    String type = scanner.nextLine();
                    System.out.print("Nom: ");
                    String nom = scanner.nextLine();
                    System.out.print("Description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Prix: ");
                    double prix = Double.parseDouble(scanner.nextLine());
                    MenuItem item = FactoryMenuItem.creerMenuItem(type, 0, nom, desc, prix);
                    menuService.ajouterMenuItem(item);
                    break;
                case 2:
                    List<MenuItem> items = menuService.listerMenuItems();
                    items.forEach(MenuItem::afficher);
                    break;
                case 3:
                    System.out.print("ID de l'élément à supprimer: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    menuService.supprimerMenuItem(id);
                    break;
                default:
                    System.out.println("Option invalide.");
            }
        } catch (Exception e) {
            System.out.println("Erreur Gestion Menu: " + e.getMessage());
        }
    }

    private void reservationManagement() {
        try {
            System.out.println("\n-- Gestion des réservations --");
            System.out.println("1. Créer une réservation");
            System.out.println("2. Lister les réservations");
            System.out.println("3. Modifier une réservation");
            System.out.println("4. Annuler une réservation");
            System.out.print("Choix: ");
            int c = Integer.parseInt(scanner.nextLine());
            switch (c) {
                case 1:
                    System.out.print("Nom du client: ");
                    String client = scanner.nextLine();
                    System.out.print("Date (yyyy-MM-dd HH:mm): ");
                    Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(scanner.nextLine());
                    System.out.print("Nombre de personnes: ");
                    int nb = Integer.parseInt(scanner.nextLine());
                    Reservation r = new Reservation(0, client, date, nb);
                    reservationService.creerReservation(r);
                    break;
                case 2:
                    reservationService.listerReservations().forEach(res -> 
                        System.out.println(res.getId() + " - " + res.getClientNom() + " @ " + res.getDateHeure())
                    );
                    break;
                case 3:
                    System.out.print("ID reservation à modifier: ");
                    int rid = Integer.parseInt(scanner.nextLine());
                    Reservation existing = reservationService.trouverReservation(rid);
                    System.out.print("Nouveau nombre de personnes: ");
                    int newNb = Integer.parseInt(scanner.nextLine());
                    existing = new Reservation(rid, existing.getClientNom(), existing.getDateHeure(), newNb);
                    reservationService.modifierReservation(existing);
                    break;
                case 4:
                    System.out.print("ID reservation à annuler: ");
                    reservationService.annulerReservation(Integer.parseInt(scanner.nextLine()));
                    break;
                default:
                    System.out.println("Option invalide.");
            }
        } catch (Exception e) {
            System.out.println("Erreur Réservation: " + e.getMessage());
        }
    }

    private void commandeManagement() {
        try {
            System.out.println("\n-- Gestion des commandes --");
            Commande cmd = new Commande(0);
            boolean adding = true;
            while (adding) {
                System.out.print("ID MenuItem à ajouter (tapez 0 pour terminer): ");
                int mid = Integer.parseInt(scanner.nextLine());
                if (mid == 0) adding = false;
                else cmd.getContent().add(menuService.findById(mid));
            }
            orderService.creerCommande(cmd);
            orderService.finaliserCommande(cmd);
        } catch (Exception e) {
            System.err.println("Erreur Commande: " + e.getMessage());
        }
    }

    private void rapportManagement() {
        try {
            System.out.println("\n-- Génération de rapport --");
            System.out.print("Type de rapport (ventes, populaires): ");
            String type = scanner.nextLine();
            Rapport r = FactoryRapport.creerRapport(type);
            r.genererRapportVentes();
        } catch (Exception e) {
            System.out.println("Erreur Rapport: " + e.getMessage());
        }
    }
}
