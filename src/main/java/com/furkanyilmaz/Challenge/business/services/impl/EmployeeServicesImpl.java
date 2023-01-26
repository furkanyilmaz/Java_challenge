package com.furkanyilmaz.Challenge.business.services.impl;

import com.furkanyilmaz.Challenge.bean.ModelMapperBean;
import com.furkanyilmaz.Challenge.business.dto.EmployeeDto;
import com.furkanyilmaz.Challenge.business.services.IEmployeeServices;
import com.furkanyilmaz.Challenge.data.entity.Employee;
import com.furkanyilmaz.Challenge.data.repository.IEmployeeRepository;
import com.furkanyilmaz.Challenge.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Log4j2

@Service
@Transactional
public class EmployeeServicesImpl implements IEmployeeServices {

    //injection
    private final IEmployeeRepository repository;
    private final ModelMapperBean modelMapperBean;


    //Model mapper
    @Override
    public EmployeeDto entityToDto(Employee employeeEntity) {
        return modelMapperBean.modelMapperMethod().map(employeeEntity,EmployeeDto.class);
    }

    @Override
    public Employee dtoToEntity(EmployeeDto employeeDto) {
        return modelMapperBean.modelMapperMethod().map(employeeDto,Employee.class);
    }

    //CREATE
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employeeEntity = dtoToEntity(employeeDto);
        repository.save(employeeEntity);
        return employeeDto;
    }

    //LIST
    @Override
    public List<EmployeeDto> listEmployee() {
        List<Employee> employeeEntityList = repository.findAll();
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        for (Employee temp : employeeEntityList){
            EmployeeDto entityToDto = entityToDto(temp);
            employeeDtoList.add(entityToDto);
        }
        return employeeDtoList;
    }

    //FIND
    @Override
    public EmployeeDto findEmployee(Long id) {
        Employee employeeEntity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id  + " id'si bulunamadi"));
        EmployeeDto entityToDto = entityToDto(employeeEntity);
        return entityToDto;
    }

    //DELETE
    @Override
    public Map<String, Boolean> deleteEmployee(Long id) {
        Employee employeeEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + "id'si bulunamadi,silinemez"));
        repository.delete(employeeEntity);
        Map<String, Boolean> response = new HashMap<>();
        response.put(id + "id'li seçilen çalışan silindi", Boolean.TRUE);
        return response;
    }

    //UPDATE
    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employeeEntity= repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id + " id'si bulunamadı, güncellenemez"));
        if (employeeEntity != null){
            employeeEntity.setName(employeeDto.getName());
            employeeEntity.setEmail(employeeDto.getEmail());
            employeeEntity.setPassword(employeeDto.getPassword());
            employeeEntity.setCompany(employeeEntity.getCompany());
            repository.save(employeeEntity);
        }
        return null;
    }
}
