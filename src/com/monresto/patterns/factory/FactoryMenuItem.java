package com.monresto.patterns.factory;
import com.monresto.model.*;

public class FactoryMenuItem {
    public static MenuItem creerMenuItem(String type,int id,String nom,String desc,double prix) {
        switch(type.toLowerCase()){
            case "plat": 
            	return new Plat(id,nom,desc,prix);
            case "boisson": 
            	return new Boisson(id,nom,desc,prix);
            case "dessert": 
            	return new Dessert(id,nom,desc,prix);
            default: 
            	throw new IllegalArgumentException("Type de menu inconnu");
        }
    }
}
