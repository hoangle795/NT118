package com.example.lab2_4;

public class Employee {
    private String id;
    private String fullName;
    private boolean isManager;

    public Employee(String id, String fullName, boolean isManager) {
        this.id = id;
        this.fullName = fullName;
        this.isManager = isManager;
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public boolean isManager() { return isManager; }
}
