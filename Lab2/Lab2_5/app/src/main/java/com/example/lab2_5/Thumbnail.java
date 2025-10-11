package com.example.lab2_5;

public class Thumbnail {
    private int imageRes;
    private String name;

    public Thumbnail(int imageRes, String name) {
        this.imageRes = imageRes;
        this.name = name;
    }

    public int getImageRes() { return imageRes; }
    public String getName() { return name; }
}

