package com.university.dao;

import com.university.entity.Lector;

import java.math.BigDecimal;
import java.util.List;

public interface LectorDao {
    List<Lector> getLectorsByDepartmentName(String departmentName);

    BigDecimal getAverageSalaryByDepartmentName(String departmentName);
}
