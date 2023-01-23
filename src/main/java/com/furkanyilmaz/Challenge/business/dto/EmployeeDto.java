package com.furkanyilmaz.Challenge.business.dto;

import com.furkanyilmaz.Challenge.data.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeDto implements Serializable {

    private long id;

    @NotEmpty(message = "{company.name.validation.constraints.NotNull.message.tr}")
    private String name;
    private String email;
    private String password;
    private String salary;
    private String company;
}
