package com.monresto.model;

public abstract class Personnel {
    protected int id;
    protected String nom;
    protected String role;

    public Personnel(int id, String nom, String role) {
        this.id = id;
        this.nom = nom;
        this.role = role;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNom() { return nom; }
    public String getRole() { return role; }
    public abstract void afficher();

    public static Personnel createByRole(int id, String nom, String role) {
        switch(role.toLowerCase()) {
            case "serveur": return new Serveur(id, nom);
            case "cuisinier": return new Cuisinier(id, nom);
            case "manager": return new Manager(id, nom);
            default: throw new IllegalArgumentException("Role inconnu");
        }
    }
}
