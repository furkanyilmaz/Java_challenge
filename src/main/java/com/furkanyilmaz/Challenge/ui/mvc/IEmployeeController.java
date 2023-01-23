package com.furkanyilmaz.Challenge.ui.mvc;

import com.furkanyilmaz.Challenge.business.dto.CompanyDto;
import com.furkanyilmaz.Challenge.business.dto.EmployeeDto;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface IEmployeeController {

    public String createSpeedDataE(Model model);

    public String validationGetEmployee(Model model);

    public String validationPostEmployee(EmployeeDto employeeDto, BindingResult bindingResult, Model model);

    public String employeeList(Model model);

    public String employeeFindById(long id, Model model);

    public String employeeDeleteById(long id,Model model);

    public String updateGetEmployee(long id, Model model);

    public String updatePostEmployee(long id, EmployeeDto employeeDto, BindingResult bindingResult, Model model);
}
