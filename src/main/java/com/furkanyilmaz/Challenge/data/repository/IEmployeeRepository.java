package com.furkanyilmaz.Challenge.data.repository;

import com.furkanyilmaz.Challenge.data.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByEmail(String email);
}
