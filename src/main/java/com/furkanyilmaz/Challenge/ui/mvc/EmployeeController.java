package com.furkanyilmaz.Challenge.ui.mvc;

import com.furkanyilmaz.Challenge.bean.ModelMapperBean;
import com.furkanyilmaz.Challenge.bean.PasswordEncoderBean;
import com.furkanyilmaz.Challenge.business.dto.EmployeeDto;
import com.furkanyilmaz.Challenge.data.entity.Employee;
import com.furkanyilmaz.Challenge.data.repository.IEmployeeRepository;
import com.furkanyilmaz.Challenge.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Log4j2

@Controller
public class EmployeeController implements IEmployeeController{


    //injects
    private final IEmployeeRepository repository;
    private final ModelMapperBean modelMapperBean;
    private final PasswordEncoderBean passwordEncoderBean;

    // SPEED DATA EMPLOYEE
    // http://localhost:8091/speedDataEmployee
    @GetMapping("/speedDataEmployee")
    public String createSpeedDataE(Model model) {
        int counter = 0;
        for (int i = 1; i <= 5; i++) {
            UUID uuid = UUID.randomUUID();
            Employee registerEmployeeEntity = Employee.builder()
                    .name(" SpringBOOT  " + i + ". Employee")
                    .email(uuid.toString().concat("@gmail.com"))
                    .password("pswd " + i)
                    .salary("10000")
//                    .blogCreatedDate(new Date(System.currentTimeMillis()))
                    .build();
            repository.save(registerEmployeeEntity);
            //model mapper kullanmadan direkt entity'i build ettik.(dto değil)
            counter++;
        }
        model.addAttribute("key_dataset", counter + " tane Blog oluşturuldu...");
        return "redirect:/blog/list";
    }

    // SPEED DELETE
    // http://localhost:8091/speedDataEmployee
    @GetMapping("/speedDeleteEmployee")
    public String deleteSpeedDataE(Model model) {
        repository.deleteAll();
        return "redirect:/employee/list";
    }

    // CREATE
    // http://localhost:8091/employee/create
    @GetMapping("/employee/create")
    public String validationGetEmployee(Model model) {
        model.addAttribute("key_employee", new EmployeeDto());
        return "employee_create";
    }

    //CREATE
    // http://localhost:8091/employee/create
    @PostMapping("/employee/create")
    @Override
    public String validationPostEmployee(@Valid @ModelAttribute("key_employee") EmployeeDto employeeDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            log.error("HATA: " + bindingResult);
            return "employee_create";
        }
        model.addAttribute("employee_success", "Employee Saved" + employeeDto);
        log.info("Success"+ employeeDto);

        //db
        //masking password
        employeeDto.setPassword(passwordEncoderBean.passwordEncoderMethod().encode(employeeDto.getPassword()));
        //model mapper
        Employee registerEmployeeEntity = modelMapperBean.modelMapperMethod().map(employeeDto, Employee.class);
        try {
            if (registerEmployeeEntity != null){
                repository.save(registerEmployeeEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    // LIST
    // http://localhost:8091/employee/list
    @GetMapping("/employee/list")
    @Override
    public String employeeList(Model model) {
        List<Employee> list = repository.findAll();
        model.addAttribute("key_employee", list);
        list.forEach((temp)->{
            System.out.println(temp);
        });
        return "employee_list";
    }

    // FIND
    // http://localhost:8091/employee/find
    // http://localhost:8091/employee/find/1
    @GetMapping( "/employee/find/{id}")
    public String employeeFindById(@PathVariable(name = "id") long id, Model model) {
        Employee registerEmployeeEntity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id + "nolu kayıt yok "));
        model.addAttribute("employee_find", registerEmployeeEntity);
        return "employee_detail_page";
    }

    // DELETE
    // http://localhost:8091/employee/delete
    // http://localhost:8091/employee/delete/1
    @GetMapping({"/daily/delete", "/daily/delete/{id}"})
    public String employeeDeleteById(@PathVariable(name = "id", required = false) long id, Model model) {
        Employee registerEmployeeEntity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id + " nolu kayıt yok, silinemez."));
        if (registerEmployeeEntity != null){
            repository.deleteById(id);
            model.addAttribute("key_delete", registerEmployeeEntity + " silindi");
        } else
            model.addAttribute("key_delete", id + "nolu veri yok");
        return "redirect:/employee/list";
    }

    //UPDATE
    // http://localhost:8091/employee/update
    @GetMapping("/employee/update/{id}")
    @Override
    public String updateGetEmployee(@PathVariable(name = "id") long id, Model model) {
        Employee registerEmployeeEntityFind = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id + "nolu kayıt yok, güncellenemez."));
        if (registerEmployeeEntityFind != null){
            model.addAttribute("key_update", registerEmployeeEntityFind);
        } else
            model.addAttribute("key_update", id + "numarali veri yok.");
        return "employee_update";
    }

    //UPDATE
    // http://localhost:8091/employee/update
    @PostMapping("/employee/update/{id}")
    @Override
    public String updatePostEmployee(@PathVariable(name = "id") long id, @Valid @ModelAttribute("key_uodate") EmployeeDto employeeDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            log.error("HATA", bindingResult);
            return "employee_update";
        }
        Employee registerEmployeeEntity = modelMapperBean.modelMapperMethod().map(employeeDto, Employee.class);
        try {
            if (registerEmployeeEntity!= null){
                repository.save(registerEmployeeEntity);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/employee/list";
    }

}
