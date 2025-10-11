package com.example.lab2_3;

public class EmployeePartTime extends Employee {

    public EmployeePartTime(String id, String name) {
        super(id, name);
    }

    @Override
    public double tinhLuong() {
        return 150.0;
    }

    @Override
    public String toString() {
        return super.toString() + " | PartTime | Lương: " + tinhLuong();
    }
}