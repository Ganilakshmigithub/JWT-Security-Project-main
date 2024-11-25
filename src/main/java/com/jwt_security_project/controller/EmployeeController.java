package com.jwt_security_project.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jwt_security_project.dtos.EmployeeDTO;
import com.jwt_security_project.dtos.PhoneNumberDTO;
import com.jwt_security_project.model.employee;
import com.jwt_security_project.service.EmpService;

@RestController
public class EmployeeController {

    @Autowired
    EmpService es;

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "Welcome to the Admin Dashboard";
    }
 
    @PostMapping("/admin/create")
    public ResponseEntity<?> createEmp(@RequestBody EmployeeDTO emp){
        Set<PhoneNumberDTO> phn=emp.getPhoneNumbers();
        employee cemp=es.addEmployee(emp,phn);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(HttpStatus.CREATED.value(),"Employee's Created in Database", cemp));
    }


    @GetMapping("/user/get")
    public ResponseEntity<?> getAllEmployees(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3")int size) {
        Page<EmployeeDTO> empPage = es.getEmployees(page, size);
        ApiResponse apiResponse = new ApiResponse(
            HttpStatus.OK.value(),
            "Employees retrieved for the page you entered..",
            empPage.getContent(),            
            empPage.getNumber(),             
            empPage.getTotalPages(),         
            empPage.getTotalElements()       
        );
    
        return ResponseEntity.ok().body(apiResponse);
    }

        
        @PutMapping("/admin/update/{employee_id}/{Phnid}")
        public ResponseEntity<?> updatePhn(@PathVariable int employee_id, @PathVariable long Phnid, @RequestBody PhoneNumberDTO updatedPhnDTO) {
        EmployeeDTO empDTO = es.FindEmpById(employee_id);
        if (empDTO == null) {
            return ResponseEntity.notFound().build();
        }
        
        if (empDTO.getPhoneNumbers() == null || empDTO.getPhoneNumbers().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        for (PhoneNumberDTO phoneDTO : empDTO.getPhoneNumbers()) {
            if (phoneDTO.getId().equals(Phnid)) {
                phoneDTO.setPhoneNumber(updatedPhnDTO.getPhoneNumber());
                es.addEmployee(empDTO,empDTO.getPhoneNumbers());
                return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(HttpStatus.CREATED.value(), "Phone number updated", updatedPhnDTO));
            }
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/user/get/{id}")
    public ResponseEntity<?> FindEmpById(@PathVariable int id) {
        EmployeeDTO empDTO = es.FindEmpById(id);
        if (empDTO != null) {
            return ResponseEntity.ok().body(new ApiResponse(HttpStatus.OK.value(), "Employee retrieved by id", empDTO));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/fetch/{designation}")
    public ResponseEntity<?> FindEmpByDesignation(@PathVariable String designation){
        List<EmployeeDTO> empDTO=es.findEmpByDesignation(designation);
        if(empDTO!=null){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse(HttpStatus.ACCEPTED.value(),"employee isnt found with designation", empDTO));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/admin/del/{id}")
    public ResponseEntity<?> deleteEmp(@PathVariable int id) {
        EmployeeDTO empDTO = es.FindEmpById(id);
        if (empDTO != null) {
            es.deleteEmp(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse(HttpStatus.ACCEPTED.value(), "Employee successfully deleted by id", empDTO));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/user/dashboard")
    public String userDashboard() {
        return "Welcome to the User Dashboard!";
    }
}
