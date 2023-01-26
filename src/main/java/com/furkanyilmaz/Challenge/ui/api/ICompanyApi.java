package com.furkanyilmaz.Challenge.ui.api;

import com.furkanyilmaz.Challenge.business.dto.CompanyDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ICompanyApi {
    //CREATE
    ResponseEntity<?> createCompany(CompanyDto companyDto);

    //LIST
    ResponseEntity<?> listCompany();

    //FIND
    ResponseEntity<CompanyDto> findCompany(Long id);

    //DELETE
    ResponseEntity<Map<String,Boolean>> deleteCompany(Long id);

    //UPDATE
    ResponseEntity<CompanyDto> updateCompany(Long id , CompanyDto companyDto);
}
