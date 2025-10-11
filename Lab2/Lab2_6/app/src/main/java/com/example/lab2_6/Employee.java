package com.example.lab2_6;

public class Employee {
    private String id;
    private String name;
    private boolean manager;

    public Employee(String id, String name, boolean manager) {
        this.id = id;
        this.name = name;
        this.manager = manager;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public boolean isManager() { return manager; }
}
