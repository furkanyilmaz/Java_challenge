package com.furkanyilmaz.Challenge.ui.api.Impl;

import com.furkanyilmaz.Challenge.business.dto.EmployeeDto;
import com.furkanyilmaz.Challenge.business.services.IEmployeeServices;
import com.furkanyilmaz.Challenge.ui.api.IEmployeeApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Log4j2

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class EmployeeApiImpl implements IEmployeeApi {

    //injection
    private final IEmployeeServices services;


    //http://localhost:8091/api/v1/employee/create
    //CREATE
    @Override
    @PostMapping("/employee/create")
    public ResponseEntity<?> createEmployee(@Valid  @RequestBody EmployeeDto employeeDto) {
        services.createEmployee(employeeDto);
        return ResponseEntity.ok(employeeDto);
    }

    //http://localhost:8091/api/v1/employee/list
    //LIST
    @Override
    @GetMapping("/employee/list")
    public ResponseEntity<?> listEmployee() {
        return ResponseEntity.ok(services.listEmployee());
    }

    //http://localhost:8091/api/v1/employee/find/1
    //FIND
    @Override
    @GetMapping("/employee/find/{id}")
    public ResponseEntity<EmployeeDto> findEmployee(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(services.findEmployee(id));
    }

    //http://localhost:8091/api/v1/employee/delete/2
    //DELETE
    @Override
    @DeleteMapping("/employee/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable(name = "id") Long id) {
        services.deleteEmployee(id);
        Map<String , Boolean> response = new HashMap<>();
        response.put(id + " id'li calisan silindi", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    //http://localhost:1111/api/v1/employee/update/3
    //UPDATE
    @Override
    @PutMapping("/employee/update/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable(name = "id") Long id, @Valid @RequestBody EmployeeDto employeeDto) {
        services.updateEmployee(id,employeeDto);
        return ResponseEntity.ok(employeeDto);
    }
}
