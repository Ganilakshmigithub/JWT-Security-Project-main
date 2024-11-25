package com.jwt_security_project.service;

import com.jwt_security_project.dtos.EmployeeDTO;
import com.jwt_security_project.dtos.PhoneNumberDTO;
import com.jwt_security_project.model.employee;
import com.jwt_security_project.model.PhoneNumbers;
import com.jwt_security_project.repository.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmpService {

    @Autowired
    EmpRepo empRepo;

    private EmployeeDTO convertToDTO(employee emp) {
        Set<PhoneNumberDTO> phoneNumberDTOs = emp.getPhoneNumbers().stream()
            .map(phone -> new PhoneNumberDTO(phone.getId(), phone.getPhoneNumber()))
            .collect(Collectors.toSet());
        return new EmployeeDTO(emp.getId(),emp.getName(),emp.getAge(),phoneNumberDTOs,emp.getAddress(),emp.getDesignation(),emp.getSalary());
    }

    private employee convertToEntity(EmployeeDTO empDTO, Set<PhoneNumberDTO> phoneNumbersDTO) {
        employee emp = new employee();
        emp.setId(empDTO.getId());
        emp.setName(empDTO.getName());
        emp.setAge(empDTO.getAge());
        emp.setAddress(empDTO.getAddress());
        emp.setDesignation(empDTO.getDesignation());
        emp.setSalary(empDTO.getSalary());
        
        Set<PhoneNumbers> phoneNumbers = phoneNumbersDTO.stream().map(dto -> new PhoneNumbers(dto.getPhoneNumber()))  //to create phone number dto
            .collect(Collectors.toSet());

        emp.setPhoneNumbers(phoneNumbers);
        return emp;
    }


    public employee addEmployee(EmployeeDTO empDTO, Set<PhoneNumberDTO> phoneNumbersDTO) {
        employee emp = convertToEntity(empDTO, phoneNumbersDTO);
        return empRepo.save(emp);
    }


        public Page<EmployeeDTO> getEmployees(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<employee> empPage = empRepo.findAll(pageable);
        Page<EmployeeDTO> dtoPage = empPage.map(this::convertToDTO);
        return dtoPage;
    }

    public EmployeeDTO FindEmpById(int id) {
        employee emp = empRepo.findById(id).orElse(null);
        if (emp != null) {
            return convertToDTO(emp); 
        }
       return null;
       }

    public List<EmployeeDTO> findEmpByDesignation(String designation) {
        List<employee> emp=empRepo.findByDesignation(designation);
        if (emp != null) {
            return emp.stream().map(this::convertToDTO).collect(Collectors.toList());
            }
            return null;
            }

    public void deleteEmp(int id) {
        empRepo.deleteById(id);
    }
    }

