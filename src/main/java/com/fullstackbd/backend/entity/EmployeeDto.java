package com.fullstackbd.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {
    private String name;
    private Date hireDate;
    private String jobTitle;
    private Integer salary;
    private Long departmentId;
    private Long reportsTo;
}
