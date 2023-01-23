package com.furkanyilmaz.Challenge.business.services;

import com.furkanyilmaz.Challenge.business.dto.CompanyDto;
import com.furkanyilmaz.Challenge.data.entity.Company;

import java.util.List;
import java.util.Map;

public interface ICompanyServices {

    CompanyDto entityToDto(Company companyEntity);
    Company dtoToEntity(CompanyDto companyDto);

    //CREATE
    CompanyDto createCompany(CompanyDto companyDto);

    //LIST
    List<CompanyDto> listCompany();

    //FIND
    CompanyDto findCompany(Long id);

    //DELETE
    Map<String, Boolean> deleteCompany(Long id);

    //UPDATE
    CompanyDto updateCompany(Long id, CompanyDto companyDto);

}
