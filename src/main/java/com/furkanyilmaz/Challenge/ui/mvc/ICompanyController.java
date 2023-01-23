package com.furkanyilmaz.Challenge.ui.mvc;

import com.furkanyilmaz.Challenge.business.dto.CompanyDto;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface ICompanyController {

    public String createSpeedDataC(Model model);

    public String validationGetCompany(Model model);

    public String validationPostCompany(CompanyDto companyDto, BindingResult bindingResult, Model model);

    public String companyList(Model model);

    public String companyFindById(long id, Model model);

    public String companyDeleteById(long id,Model model);

    public String updateGetCompany(long id, Model model);

    public String updatePostCompany(long id, CompanyDto companyDto, BindingResult bindingResult, Model model);
}
