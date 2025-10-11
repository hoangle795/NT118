package com.example.lab2_5;

public class Dish {
    private String name;
    private int thumbnail;
    private boolean promo;

    public Dish(String name, int thumbnail, boolean promo) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.promo = promo;
    }

    public String getName() { return name; }
    public int getThumbnail() { return thumbnail; }
    public boolean isPromo() { return promo; }
}

