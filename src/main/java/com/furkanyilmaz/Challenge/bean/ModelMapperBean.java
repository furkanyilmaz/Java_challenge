package com.furkanyilmaz.Challenge.bean;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperBean { //dto to entity, entity to dto (çevirmek için)
    @Bean
    public ModelMapper modelMapperMethod(){
        return new ModelMapper();
    }
}
