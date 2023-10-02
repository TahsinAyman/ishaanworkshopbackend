package com.fullstackbd.backend.controller;

import com.fullstackbd.backend.entity.Employee;
import com.fullstackbd.backend.entity.EmployeeDto;
import com.fullstackbd.backend.entity.Message;
import com.fullstackbd.backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService service;

    @GetMapping("/")
    public List<Employee> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Employee employee = this.service.findById(id);
        if (employee != null) {
            return ResponseEntity.status(HttpStatus.OK).body(employee);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Message
                        .builder()
                        .message("Employee Not Found")
                        .result(false)
                        .status(HttpStatus.NOT_FOUND.value())
                        .build()
        );
    }

    @PostMapping("/")
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee savedEmployee = this.service.addEmployee(employeeDto);
        if (savedEmployee != null) {
            return ResponseEntity.status(HttpStatus.OK).body(savedEmployee);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Message
                        .builder()
                        .message("Couldn't Save Employee")
                        .result(false)
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDto employee, @PathVariable("id") Long id)     {
        Employee updatedEmployee = this.service.updateEmployee(id, employee);
        if (updatedEmployee != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Message
                        .builder()
                        .message("Couldn't Update Employee")
                        .result(false)
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        Employee deletedEmployee = this.service.deleteEmployee(id);
        if (deletedEmployee != null) {
            return ResponseEntity.status(HttpStatus.OK).body(deletedEmployee);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Message
                        .builder()
                        .message("Couldn't Delete Employee")
                        .result(false)
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build()
        );
    }



}
