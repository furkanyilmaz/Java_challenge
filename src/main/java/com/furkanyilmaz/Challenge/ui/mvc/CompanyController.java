package com.furkanyilmaz.Challenge.ui.mvc;

import com.furkanyilmaz.Challenge.bean.ModelMapperBean;
import com.furkanyilmaz.Challenge.business.dto.CompanyDto;
import com.furkanyilmaz.Challenge.data.entity.Company;
import com.furkanyilmaz.Challenge.data.repository.ICompanyRepository;
import com.furkanyilmaz.Challenge.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Log4j2

@Controller
public class CompanyController implements ICompanyController{

    //injects
    private final ICompanyRepository repository;
    private final ModelMapperBean modelMapperBean;

    // SPEED DATA
    // http://localhost:8091/speedDataC
    @GetMapping("/speedDataC")
    public String createSpeedDataC(Model model) {
        int counter= 0;
        for (int i = 1; i<=5; i++) {
            UUID uuid = UUID.randomUUID();
            Company companyEntity = Company.builder()
                    .name("Company Name "+ i).owner("OwnerName "+i)
                    .build();
            repository.save(companyEntity);
            counter++;
        }
        model.addAttribute("key_dataset", counter + " tane company Entity olustu.");
        return "redirect:/company/list";
    }

    // SPEED DELETE Company
    // http://localhost:8091/speedDataC
    @GetMapping("/speedDeleteC")
    public String deleteSpeedDataC(Model model) {
        repository.deleteAll();
        return "redirect:/company/list";
    }

    // CREATE
    // http://localhost:8091/company/create
    @GetMapping("/company/create")
    public String validationGetCompany(Model model) {
        model.addAttribute("key_company", new CompanyDto());
        return "company_create";
    }

    //CREATE
    // http://localhost:8091/company/create
    @PostMapping("/company/create")
    public String validationPostCompany(@Valid @ModelAttribute("key_company") CompanyDto companyDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            log.error("HATA: "+ bindingResult);
            return "company_create";
        }
        model.addAttribute("company_success", "Company Saved"+ companyDto);
        log.info("Success "+ companyDto);

        Company companyEntity = modelMapperBean.modelMapperMethod().map(companyDto, Company.class);
        try {
            if (companyEntity !=null){
                repository.save(companyEntity);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }

    // LIST
    // http://localhost:8091/company/list
    @GetMapping("/company/list")
    public String companyList(Model model) {
        List<Company> list = repository.findAll();
        model.addAttribute("key_company", list);
        list.forEach((temp)->{
            System.out.println(temp);
        });
        return "company_list";
    }

    // FIND
    // http://localhost:8091/company/find
    // http://localhost:8091/company/find/1
    @GetMapping( "/company/find/{id}")
    public String companyFindById(@PathVariable(name = "id") long id, Model model) {
        Company companyEntity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id + "nolu sirket bulunamadi"));
        model.addAttribute("company_find", companyEntity);
        return "company_detail_page";
    }

    // DELETE
    // http://localhost:8091/company/delete
    // http://localhost:8091/company/delete/1
    @GetMapping({"/company/delete", "/company/delete/{id}"})
    public String companyDeleteById(@PathVariable(name = "id", required = false) long id, Model model) {
        Company companyEntity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id + "nolu sirket bulunamadi"));
        if (companyEntity != null){
            repository.deleteById(id);
            model.addAttribute("key_delete", companyEntity + "sirket silindi.");
        } else
            model.addAttribute("key_delete", id + "numarali sirket yok");
        return "redirect:/company/list";
    }

    //UPDATE
    // http://localhost:8091/company/update
    @GetMapping("/company/update/{id}")
    public String updateGetCompany(@PathVariable(name = "id") long id, Model model) {
        Company companyEntityFind = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id+ "numarali sirket yok"));
        if (companyEntityFind !=null){
            model.addAttribute("key_update", companyEntityFind);
        } else
            model.addAttribute("key_update", id + "numarali sirket yok");
        return "company_update";
    }

    //UPDATE
    // http://localhost:8091/company/update
    @PostMapping("/company/update/{id}")
    public String updatePostCompany(@PathVariable(name = "id") long id, @Valid @ModelAttribute("key_update") CompanyDto companyDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.error("HATA: " + bindingResult);
            return "company_update";
        }
        Company companyEntity = modelMapperBean.modelMapperMethod().map(companyDto, Company.class);
        try {
            if (companyEntity!= null){
                repository.save(companyEntity);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/company/list";
    }
}
