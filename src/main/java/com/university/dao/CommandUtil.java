package com.university.dao;

import com.university.entity.Department;
import com.university.entity.Lector;

import java.util.List;

public interface CommandUtil {

    void showDepartmentStatisticByDepName(String departmentName);

    Department getDepartmentByName(String departmentName);

    List<Lector> getLectorsByDepartmentName(String departmentName);

    void showAverageSalaryByDepartmentName(String departmentName);

    void showLectorsCountByDepartmentName(String departmentName);

    void searchByKeyword(String keyword);

    void showDepartmentChief(String departmentName);

}
