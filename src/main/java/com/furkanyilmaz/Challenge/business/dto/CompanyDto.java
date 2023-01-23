package com.furkanyilmaz.Challenge.business.dto;

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
public class CompanyDto implements Serializable {

    private long id;

    @NotEmpty(message = "{company.name.validation.constraints.NotNull.message.tr}")
    private String name;

    private String owner;

    private String employees;
}
