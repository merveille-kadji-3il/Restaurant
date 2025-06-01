-- File: schema.sql
-- Script PostgreSQL pour créer toutes les tables de l’application

-- 1. TABLE “personnel”
CREATE TABLE IF NOT EXISTS personnel (
  id    SERIAL PRIMARY KEY,
  nom   VARCHAR(100) NOT NULL,
  role  VARCHAR(20)  NOT NULL  -- “Serveur” | “Cuisinier” | “Manager”
);

-- 2. TABLE “table_restaurant”
CREATE TABLE IF NOT EXISTS table_restaurant (
  numero   INTEGER      PRIMARY KEY,
  capacite INTEGER      NOT NULL,
  etat     VARCHAR(10)  NOT NULL DEFAULT 'libre'  -- “libre” | “reservee” | “occupee”
);

-- 3. TABLE “reservation”
CREATE TABLE IF NOT EXISTS reservation (
  id                SERIAL PRIMARY KEY,
  client_nom        VARCHAR(100) NOT NULL,
  date_heure        TIMESTAMP      NOT NULL,
  nombre_personnes  INTEGER        NOT NULL,
  table_numero      INTEGER,
  CONSTRAINT fk_resv_table
    FOREIGN KEY (table_numero)
    REFERENCES table_restaurant(numero)
    ON DELETE SET NULL
);

-- 4. TABLE “ingredient”
CREATE TABLE IF NOT EXISTS ingredient (
  id             SERIAL PRIMARY KEY,
  nom            VARCHAR(100) NOT NULL,
  quantite_stock INTEGER       NOT NULL
);

-- 5. TABLE “menuitem”
--   Stocke les Plats, Boissons et Desserts dans une seule table.
CREATE TABLE IF NOT EXISTS menuitem (
  id          SERIAL       PRIMARY KEY,
  nom         VARCHAR(100) NOT NULL,
  description TEXT,
  prix        NUMERIC(10,2) NOT NULL,
  type        VARCHAR(20)  NOT NULL  -- “Plat” | “Boisson” | “Dessert”
);

-- 6. TABLE “menuitem_ingredient” (association N–N)
--   Lien entre chaque MenuItem et ses ingrédients ainsi que la quantité requise.
CREATE TABLE IF NOT EXISTS menuitem_ingredient (
  menuitem_id      INTEGER NOT NULL,
  ingredient_id    INTEGER NOT NULL,
  quantite_requise INTEGER NOT NULL,
  PRIMARY KEY (menuitem_id, ingredient_id),
  CONSTRAINT fk_mi_menuitem
    FOREIGN KEY (menuitem_id)
    REFERENCES menuitem(id)
    ON DELETE CASCADE,
  CONSTRAINT fk_mi_ingredient
    FOREIGN KEY (ingredient_id)
    REFERENCES ingredient(id)
    ON DELETE CASCADE
);

-- 7. TABLE “commande”
CREATE TABLE IF NOT EXISTS commande (
  id            SERIAL       PRIMARY KEY,
  date_commande TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  etat          VARCHAR(20)  NOT NULL  -- “Nouvelle” | “EnCours” | “Prete” | “Livree” | “Payee”
);

-- 8. TABLE “commande_menuitem” (association N–N)
--   Contient les MenuItems inclus dans chaque commande, avec quantité (par défaut 1).
CREATE TABLE IF NOT EXISTS commande_menuitem (
  commande_id  INTEGER NOT NULL,
  menuitem_id  INTEGER NOT NULL,
  quantite     INTEGER NOT NULL DEFAULT 1,
  PRIMARY KEY (commande_id, menuitem_id),
  CONSTRAINT fk_cm_commande
    FOREIGN KEY (commande_id)
    REFERENCES commande(id)
    ON DELETE CASCADE,
  CONSTRAINT fk_cm_menuitem
    FOREIGN KEY (menuitem_id)
    REFERENCES menuitem(id)
    ON DELETE CASCADE
);

-- 9. TABLE “facture”
--   Liée 1–1 à une commande : commande_id est UNIQUE.
CREATE TABLE IF NOT EXISTS facture (
  id              SERIAL       PRIMARY KEY,
  commande_id     INTEGER      UNIQUE NOT NULL,
  montant_total   NUMERIC(10,2) NOT NULL,
  statut_paiement VARCHAR(20)  NOT NULL DEFAULT 'en_attente',  -- “en_attente” | “payee”
  CONSTRAINT fk_fact_commande
    FOREIGN KEY (commande_id)
    REFERENCES commande(id)
    ON DELETE CASCADE
);

-- 10. TABLE “personnel_table” (association N–N)
--    Affecte un membre du personnel à une (ou plusieurs) table(s) du restaurant.
CREATE TABLE IF NOT EXISTS personnel_table (
  personnel_id  INTEGER NOT NULL,
  table_numero  INTEGER NOT NULL,
  PRIMARY KEY (personnel_id, table_numero),
  CONSTRAINT fk_pt_personnel
    FOREIGN KEY (personnel_id)
    REFERENCES personnel(id)
    ON DELETE CASCADE,
  CONSTRAINT fk_pt_table
    FOREIGN KEY (table_numero)
    REFERENCES table_restaurant(numero)
    ON DELETE CASCADE
);