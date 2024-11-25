package com.jwt_security_project.service;

import com.jwt_security_project.dtos.AppUserDTO;
import com.jwt_security_project.model.AppUser;
import com.jwt_security_project.repository.AppUserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Converts AppUserDTO to AppUser entity
    private AppUser convertToEntity(AppUserDTO userDTO) {
        AppUser appUser = new AppUser();
        appUser.setUsername(userDTO.getUsername());
        appUser.setPassword(userDTO.getPassword()); 
        appUser.setRole(userDTO.getRole());
        return appUser;
    }

    // Registers a user by encoding the password and saving the AppUser entity to the database
    public AppUser registerUser(AppUserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        
        // Convert the DTO to entity
        AppUser appUser = convertToEntity(userDTO);
        return appUserRepository.save(appUser);
    }





}
