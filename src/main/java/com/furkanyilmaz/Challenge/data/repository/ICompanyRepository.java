package com.furkanyilmaz.Challenge.data.repository;

import com.furkanyilmaz.Challenge.data.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompanyRepository extends JpaRepository<Company, Long> {

}
