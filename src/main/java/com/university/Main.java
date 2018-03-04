package com.university;

import com.university.dao.DepartmentDao;
import com.university.dao.DepartmentDaoImpl;
import com.university.dao.LectorDao;
import com.university.dao.LectorDaoImpl;
import com.university.entity.Department;
import com.university.entity.Lector;
import com.university.util.DataBaseUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        DepartmentDao departmentDao = new DepartmentDaoImpl();
        LectorDao lectorDao = new LectorDaoImpl();


        Department department = departmentDao.getDepartmentByName("Law department");
        System.out.println(department.getChief());


        BigDecimal avgSalary = lectorDao.getAverageSalaryByDepartmentName("Medical department");
        System.out.println(avgSalary);


    }
}
