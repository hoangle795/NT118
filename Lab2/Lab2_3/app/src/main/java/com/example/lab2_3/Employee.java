package com.example.lab2_3;

public abstract class Employee {
    protected String id;
    protected String name;

    public Employee() {}

    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }

    public abstract double tinhLuong();

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name;
    }
}
