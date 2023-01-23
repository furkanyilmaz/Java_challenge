package com.furkanyilmaz.Challenge.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD}) //@kullanacağımız yer.
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqueEmailValidation.class}) //bir class'a sign etmemiz gerekiyor.
public @interface UserRegisterUniqueEmail {

    String message() default "{employee.unique.email.validation.constraints.NotNull.message.tr}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
