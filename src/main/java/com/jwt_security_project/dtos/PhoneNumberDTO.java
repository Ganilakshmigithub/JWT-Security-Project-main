package com.jwt_security_project.dtos;


public class PhoneNumberDTO {
    private Long id;
    private String phoneNumber;
    public PhoneNumberDTO(long id, String phoneNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
