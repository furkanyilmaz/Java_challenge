package com.furkanyilmaz.Challenge.ui.api.Impl;

import com.furkanyilmaz.Challenge.business.dto.CompanyDto;
import com.furkanyilmaz.Challenge.business.services.ICompanyServices;
import com.furkanyilmaz.Challenge.ui.api.ICompanyApi;
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
public class CompanyApiImpl implements ICompanyApi {

    //injects
    private final ICompanyServices services;

    //http://localhost:8091/api/v1/company/create
    //CREATE
    @Override
    @PostMapping("/company/create")
    public ResponseEntity<?> createCompany (@Valid @RequestBody CompanyDto companyDto) {
        services.createCompany(companyDto);
        return ResponseEntity.ok(companyDto);
    }


    //http://localhost:8091/api/v1/company/list
    //LIST
    @Override
    @GetMapping("/company/list")
    public ResponseEntity<?> listCompany() {
        return ResponseEntity.ok(services.listCompany());
    }

    //http://localhost:8091/api/v1/company/find/1
    //FIND
    @Override
    @GetMapping("/company/find/{id}")
    public ResponseEntity<CompanyDto> findCompany(Long id) {
        return ResponseEntity.ok(services.findCompany(id));
    }

    //http://localhost:8091/api/v1/company/delete/2
    //DELETE
    @Override
    @DeleteMapping("/company/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCompany(@PathVariable (name = "id") Long id) {
        services.deleteCompany(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("company deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    //http://localhost:8091/api/v1/company/update/3
    //UPDATE
    @Override
    @PutMapping("/company/update/{id}")
    public ResponseEntity<CompanyDto> update(@PathVariable(name = "id") Long id, @Valid @RequestBody CompanyDto companyDto) {
        services.updateCompany(id,companyDto);
        return ResponseEntity.ok(companyDto);
    }
}
