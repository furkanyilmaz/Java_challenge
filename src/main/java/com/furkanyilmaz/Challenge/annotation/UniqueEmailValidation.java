package com.furkanyilmaz.Challenge.annotation;


import com.furkanyilmaz.Challenge.data.entity.Employee;
import com.furkanyilmaz.Challenge.data.repository.IEmployeeRepository;
import lombok.RequiredArgsConstructor;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor //repoyu kullanabil
public class UniqueEmailValidation implements ConstraintValidator<UserRegisterUniqueEmail,String> {
    //repoyu çağır

    private final IEmployeeRepository repository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context){

        Employee registerEntity = repository.findByEmail(email);
        if (registerEntity!= null)
            return false;
        return true;
    }


}
