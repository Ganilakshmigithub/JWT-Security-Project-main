package com.jwt_security_project.dtos;

import java.util.HashSet;
import java.util.Set;


public class EmployeeDTO {
    private int id;
    String name;
    int age;
    Set<PhoneNumberDTO> phoneNumbers = new HashSet<>();
    private String address;
    private String designation;
    private int salary;
    public String getName() {
        return name;
    }    
    public EmployeeDTO(int id, String name, int age, Set<PhoneNumberDTO> phoneNumbers, String address,
            String designation, int salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phoneNumbers = phoneNumbers;
        this.address = address;
        this.designation = designation;
        this.salary = salary;
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
    public Set<PhoneNumberDTO> getPhoneNumbers() {
        return phoneNumbers;
    }
    public void setPhoneNumbers(Set<PhoneNumberDTO> phoneNumbers) {
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

