package com.example.lab03_2;

public class Student {
    private int id;
    private String name;
    private String mssv;
    private String birthYear;
    private String major;
    private String className;

    public Student() {}

    public Student(String name, String mssv, String birthYear, String major, String className) {
        this.name = name;
        this.mssv = mssv;
        this.birthYear = birthYear;
        this.major = major;
        this.className = className;
    }

    public Student(int id, String name, String mssv, String birthYear, String major, String className) {
        this.id = id;
        this.name = name;
        this.mssv = mssv;
        this.birthYear = birthYear;
        this.major = major;
        this.className = className;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public String getMssv() { return mssv; }
    public String getBirthYear() { return birthYear; }
    public String getMajor() { return major; }
    public String getClassName() { return className; }

    public void setName(String name) { this.name = name; }
    public void setMssv(String mssv) { this.mssv = mssv; }
    public void setBirthYear(String birthYear) { this.birthYear = birthYear; }
    public void setMajor(String major) { this.major = major; }
    public void setClassName(String className) { this.className = className; }
}

