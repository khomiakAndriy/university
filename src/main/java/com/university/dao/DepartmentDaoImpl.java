package com.university.dao;

import com.university.entity.Department;
import com.university.entity.Lector;
import com.university.util.DataBaseUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.util.List;


public class DepartmentDaoImpl implements DepartmentDao {
    private SessionFactory sessionFactory = DataBaseUtil.getSessionFactory();

    @Override
    public String getChief(String departmentName) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null;
        String chief = null;
        try{
            transaction = session.beginTransaction();
            chief = session.createNamedQuery(Department.GET_DEPARTMENT_BY_NAME, Department.class).setParameter("name", departmentName).getSingleResult().getChief();
        } finally {
            if (transaction != null) transaction.commit();
        }
        return chief;
    }

    @Override
    public Department getDepartmentByName(String departmentName) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null;
        Department department = null;
        try{
            transaction = session.beginTransaction();
            department = session.createNamedQuery(Department.GET_DEPARTMENT_BY_NAME, Department.class).setParameter("name", departmentName).getSingleResult();
        } finally {
            if (transaction != null) transaction.commit();
        }
        return department;
    }
}
