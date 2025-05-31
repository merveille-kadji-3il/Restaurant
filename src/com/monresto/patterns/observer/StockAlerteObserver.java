package com.monresto.patterns.observer;

public class StockAlerteObserver implements Observer {
    @Override
    public void notifier(String message) {
        System.out.println("[ALERTE STOCK] " + message);
    }
}
