package com.accenture.webapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.accenture.webapp.domain.Employee;


public interface EmployeeRepository extends CrudRepository<Employee, String>{}
