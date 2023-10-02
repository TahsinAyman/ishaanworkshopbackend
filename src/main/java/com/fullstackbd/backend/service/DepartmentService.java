package com.fullstackbd.backend.service;

import com.fullstackbd.backend.entity.Department;
import com.fullstackbd.backend.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findById(Long id) {
        return this.departmentRepository.findById(id).orElse(null);
    }

    public Department addDepartment(Department department) {
        try {
            return this.departmentRepository.save(department);
        } catch (Exception e) {
            return null;
        }
    }
    public Department updateDepartment(Long id, Department department) {
        Department d = this.findById(id);
        if (d == null) {
            return null;
        }
        if (department.getName() != null) {
            d.setName(department.getName());
        }
        if (department.getAddress() != null) {
            d.setAddress(department.getAddress());
        }
        return departmentRepository.save(d);
    }
    public Department deleteDepartment(Long id) {
        Department department = this.findById(id);
        if (department != null) {
            departmentRepository.deleteById(id);
            return department;
        }
        return null;
    }
}
