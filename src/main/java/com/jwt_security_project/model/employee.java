package com.jwt_security_project.model;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    String name;
    int age;
    @OneToMany(cascade = CascadeType.ALL,fetch =FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "employee_id")  
    Set<PhoneNumbers> phoneNumbers = new HashSet<>();
    String address;
    String designation;
    int salary;
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
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Set<PhoneNumbers> getPhoneNumbers() {
        return phoneNumbers;
    }
    public void setPhoneNumbers(Set<PhoneNumbers> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
}
