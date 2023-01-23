package com.furkanyilmaz.Challenge.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "Employee")
public class Employee extends BaseEntity implements Serializable {
    public static final long serialVersionUID=1L;

    @SequenceGenerator(name = "employee", sequenceName = "SEHIR_ID_SEQ" )
    @Id
    @GeneratedValue( generator = "employee" , strategy = GenerationType.SEQUENCE)
    // @Column(nullable = false)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String salary;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false
    )
    private Company company;



}
