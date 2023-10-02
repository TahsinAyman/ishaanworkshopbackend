package com.fullstackbd.backend.service;

import com.fullstackbd.backend.entity.Department;
import com.fullstackbd.backend.entity.Employee;
import com.fullstackbd.backend.entity.EmployeeDto;
import com.fullstackbd.backend.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository repository;
    private DepartmentService departmentService;

    private Employee convertDtoToObj(EmployeeDto dto) {
        Department department = null;
        Employee reportsTo = null;
        if (dto.getDepartmentId() != null) {
            department = departmentService.findById(dto.getDepartmentId());
        }
        if (dto.getReportsTo() != null) {
            reportsTo = this.findById(dto.getReportsTo());
        }
        return Employee
                .builder()
                .name(dto.getName())
                .jobTitle(dto.getJobTitle())
                .hireDate(dto.getHireDate())
                .department(department)
                .reportsTo(reportsTo)
                .build();
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public Employee findById(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    public Employee addEmployee(EmployeeDto employeeDto) {
        try {
            Employee employee = this.convertDtoToObj(employeeDto);
            return this.repository.save(employee);
        } catch (Exception e) {
            return null;
        }
    }
    public Employee updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee e = this.findById(id);
        if (e == null) {
            return null;
        }
        Employee employee = this.convertDtoToObj(employeeDto);
        if (employee.getName() != null) {
            e.setName(employee.getName());
        }
        if (employee.getJobTitle() != null) {
            e.setJobTitle(employee.getJobTitle());
        }
        if (employee.getHireDate() != null) {
            e.setHireDate(employee.getHireDate());
        }
        if (employee.getDepartment() != null) {
            e.setDepartment(employee.getDepartment());
        }
        if (employee.getReportsTo() != null) {
            e.setReportsTo(employee.getReportsTo());
        }
        try {
            return repository.save(e);
        } catch (Exception exception) {
            return null;
        }

    }
    public Employee deleteEmployee(Long id) {
        Employee employee = this.findById(id);
        if (employee != null) {
            repository.deleteById(id);
            return employee;
        }
        return null;
    }
}
