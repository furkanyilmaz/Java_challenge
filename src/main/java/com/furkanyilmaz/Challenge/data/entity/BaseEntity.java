package com.furkanyilmaz.Challenge.data.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
//lombok
@Data
//super class
@MappedSuperclass

@EntityListeners(AuditingEntityListener.class)
abstract public class BaseEntity {


    @Column(name ="system_created_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date systemCreatedDate;


    //auditing
    //kim ekledi
    @Column(name="created_by")
    @CreatedBy
    private String createdBy;

    //kim ne zaman ekledi
    @Column(name="created_date")
    @CreatedDate
    private Date createdDate;

    //kim güncelledi
    @Column(name="last_modified_by")
    @LastModifiedBy
    private String lastModifiedBy;

    //kim ne zaman güncelledi
    @Column(name="last_modified_date")
    @LastModifiedDate
    private Date lastModifiedDate;

}
