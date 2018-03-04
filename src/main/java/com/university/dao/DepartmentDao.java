package com.university.dao;

import com.university.entity.Department;
import com.university.entity.Lector;

import java.util.List;

public interface DepartmentDao {
    String getChief(String departmentName);

    Department getDepartmentByName(String departmentName);
}
