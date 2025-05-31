package com.monresto;

import com.monresto.facade.RestaurantFacade;

public class App {
 public static void main(String[] args) {
     try {
         // Démarre la boucle console
         new RestaurantFacade().demarrer();
     } catch (Exception e) {
         e.printStackTrace();
     }
 }
}

