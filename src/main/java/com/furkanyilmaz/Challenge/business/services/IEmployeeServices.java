package com.furkanyilmaz.Challenge.business.services;

import com.furkanyilmaz.Challenge.business.dto.CompanyDto;
import com.furkanyilmaz.Challenge.business.dto.EmployeeDto;
import com.furkanyilmaz.Challenge.data.entity.Employee;

import java.util.List;
import java.util.Map;

public interface IEmployeeServices {

    EmployeeDto entityToDto(Employee employeeEntity);
    Employee dtoToEntity(EmployeeDto employeeDto);

    //CREATE
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    //LIST
    List<EmployeeDto> listEmployee();

    //FIND
    EmployeeDto findEmployee(Long id);

    //DELETE
    Map<String, Boolean> deleteEmployee(Long id);

    //UPDATE
    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);
}
