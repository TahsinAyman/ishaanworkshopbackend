package com.fullstackbd.backend.controller;

import com.fullstackbd.backend.entity.Department;
import com.fullstackbd.backend.entity.Message;
import com.fullstackbd.backend.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/departments")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService service;

    @GetMapping("/")
    public List<Department> findAll() {
        return service.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Department department = service.findById(id);
        if (department != null) {
            return ResponseEntity.status(HttpStatus.OK).body(department);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Message
                        .builder()
                        .message("Department Not found")
                        .result(false)
                        .status(HttpStatus.NOT_FOUND.value())
                        .build()

        );
    }

    @PostMapping("/")
    public ResponseEntity<?> addDepartment(@RequestBody Department department) {
        Department savedDepartment = service.addDepartment(department);
        if (savedDepartment != null) {
            return ResponseEntity.status(HttpStatus.OK).body(department);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            Message
                    .builder()
                    .message("Couldn't Save Department")
                    .result(false)
                    .status(HttpStatus.BAD_REQUEST.value())
                    .build()
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable("id") Long id, @RequestBody Department department) {
        Department updatedDepartment = service.updateDepartment(id, department);
        if (updatedDepartment != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedDepartment);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Message
                        .builder()
                        .message("Department Not found")
                        .result(false)
                        .status(HttpStatus.NOT_FOUND.value())
                        .build()
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("id") Long id) {
        Department deletedDepartment = service.deleteDepartment(id);
        if (deletedDepartment != null) {
            return ResponseEntity.status(HttpStatus.OK).body(deletedDepartment);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Message
                        .builder()
                        .message("Department Not found")
                        .result(false)
                        .status(HttpStatus.NOT_FOUND.value())
                        .build()
        );
    }

}
