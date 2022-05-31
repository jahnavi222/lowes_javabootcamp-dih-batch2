package com.lowes.empapp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Employee {

    private int empId;
    private String name;
    private int age;
    private String department;
    private String designation;
    private String country;

    private LocalDate doj;

    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;

    private static final String FIELD_DELIM = ",";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getDoj() {
        return doj;
    }

    public void setDoj(LocalDate doj) {
        this.doj = doj;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public String toString() {

        return (this.getEmpId()+FIELD_DELIM+this.getName()+FIELD_DELIM+this.getAge()+FIELD_DELIM
                +this.getDepartment()+FIELD_DELIM+this.getDesignation()+FIELD_DELIM
                +this.getCountry()+FIELD_DELIM+this.getDoj().toString()+FIELD_DELIM
                +this.getCreatedTime().format(formatter)+FIELD_DELIM
                +this.getModifiedTime().format(formatter)
        );
    }

    public Employee() {

    }

    public Employee(int empId, String name, int age, String department,
                    String designation, String country, LocalDate doj,
                    LocalDateTime createdTime, LocalDateTime modifiedTime){
        this.empId = empId;
        this.name = name;
        this.age = age;
        this.department = department;
        this.designation = designation;
        this.country = country;
        this.doj = doj;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }
}
