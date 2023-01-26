package com.furkanyilmaz.Challenge.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name="Company")
public class Company extends BaseEntity implements Serializable {
    public static final long serialVersionUID=1L;

//    @SequenceGenerator(name = "company", sequenceName = "COMPANY_ID_SEQ")
//    @Id
//    @GeneratedValue( generator = "company" , strategy = GenerationType.SEQUENCE)
//    // @Column(nullable = false)
//    private Long id;

    @SequenceGenerator(name = "company", sequenceName = "Company_ID_SEQ")
    @Id
    @GeneratedValue(generator = "company", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String owner;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "company"
    )
        private Set<Employee> employees = new HashSet<Employee>();
}
