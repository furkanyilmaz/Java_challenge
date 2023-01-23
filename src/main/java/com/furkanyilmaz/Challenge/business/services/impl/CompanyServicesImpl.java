package com.furkanyilmaz.Challenge.business.services.impl;

import com.furkanyilmaz.Challenge.bean.ModelMapperBean;
import com.furkanyilmaz.Challenge.business.dto.CompanyDto;
import com.furkanyilmaz.Challenge.business.services.ICompanyServices;
import com.furkanyilmaz.Challenge.data.entity.Company;
import com.furkanyilmaz.Challenge.data.repository.ICompanyRepository;
import com.furkanyilmaz.Challenge.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@RequiredArgsConstructor
@Log4j2

@Service
@Transactional
public class CompanyServicesImpl implements ICompanyServices {

    //injection
    private final ICompanyRepository repository;
    private final ModelMapperBean modelMapperBean;
    @Override
    public CompanyDto entityToDto(Company companyEntity) {
        return modelMapperBean.modelMapperMethod().map(companyEntity,CompanyDto.class);
    }

    @Override
    public Company dtoToEntity(CompanyDto companyDto) {
        return modelMapperBean.modelMapperMethod().map(companyDto, Company.class);
    }


    //CREATE
    @Override
    public CompanyDto createCompany(CompanyDto companyDto) {
        Company registerCompanyEntity = dtoToEntity(companyDto);
        repository.save(registerCompanyEntity);
        return companyDto;
    }

    //LIST
    @Override
    public List<CompanyDto> listCompany() {
        List<Company> companyEntityList = repository.findAll();
        List<CompanyDto> companyDtoList = new ArrayList<>();
        for (Company temp : companyEntityList){
            CompanyDto entityToDto = entityToDto(temp);
            companyDtoList.add(entityToDto);
        }
        return companyDtoList;
    }

    //FIND
    @Override
    public CompanyDto findCompany(Long id) {
        Company companyEntity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id + " nolu kayıt yok"));
        CompanyDto entityToDto = entityToDto(companyEntity);
        return entityToDto;
    }

    //DELETE
    @Override
    public Map<String, Boolean> deleteCompany(Long id) {
        Company companyEntity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id + " nolu kayıt yok"));
        repository.delete(companyEntity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("company deleted", Boolean.TRUE);
        return response;
    }

    //UPDATE
    @Override
    public CompanyDto updateCompany(Long id, CompanyDto companyDto) {
        Company companyEntity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id +" nolu kayıt yok, güncellenemez"));
        if (companyEntity != null){
            companyEntity.setName(companyDto.getName());
            companyEntity.setOwner(companyDto.getOwner());
            companyEntity.setEmployees(Collections.singleton(companyDto.getEmployees())); //collection singleton ?
            repository.save(companyEntity);
        }
        return null;
    }
}
