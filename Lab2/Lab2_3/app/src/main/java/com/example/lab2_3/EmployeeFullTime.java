package com.example.lab2_3;

public class EmployeeFullTime extends Employee {

    public EmployeeFullTime(String id, String name) {
        super(id, name);
    }

    @Override
    public double tinhLuong() {
        return 500.0;
    }

    @Override
    public String toString() {
        return super.toString() + " | FullTime | Lương: " + tinhLuong();
    }
}
