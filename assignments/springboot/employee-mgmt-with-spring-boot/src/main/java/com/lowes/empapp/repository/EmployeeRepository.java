package com.lowes.empapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lowes.empapp.model.Employee1;

public interface EmployeeRepository extends JpaRepository<Employee1, Integer>{
	@Query(value = "select * from employee1 order by id  desc limit 1", nativeQuery = true)
	int findByLastRecordId();

}
