package com.furkanyilmaz.Challenge.ui.api;

import com.furkanyilmaz.Challenge.business.dto.CompanyDto;
import com.furkanyilmaz.Challenge.business.dto.EmployeeDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IEmployeeApi {
    //CREATE
    ResponseEntity<?> createEmployee(EmployeeDto employeeDto);

    //LIST
    ResponseEntity<?> listEmployee();

    //FIND
    ResponseEntity<EmployeeDto> findEmployee(Long id);

    //DELETE
    ResponseEntity<Map<String,Boolean>> deleteEmployee(Long id);

    //UPDATE
    ResponseEntity<EmployeeDto> update(Long id , EmployeeDto employeeDto);
}
