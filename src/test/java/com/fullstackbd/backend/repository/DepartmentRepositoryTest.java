package com.fullstackbd.backend.repository;

import com.fullstackbd.backend.entity.Department;
import com.fullstackbd.backend.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Test
    void test() {
        System.out.println(repository.findAll());
        Department department = repository.findById(1L).get();
        Employee reporter = Employee
                .builder()
                .department(department)
                .hireDate(new Date())
                .jobTitle("CEO")
                .name("Rashed Karim")
                .build();

        Employee employee = Employee
                .builder()
                .department(department)
                .hireDate(new Date())
                .jobTitle("Full Stack Developer")
                .name("Tahsin Ayman")
                .reportsTo(reporter)
                .build();
        employeeRepository.save(reporter);
        employeeRepository.save(employee);
    }
}