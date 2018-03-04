package com.university.dao;

import com.university.entity.Department;
import com.university.entity.Lector;
import com.university.util.DataBaseUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class LectorDaoImpl implements LectorDao {
    private SessionFactory sessionFactory = DataBaseUtil.getSessionFactory();
    private DepartmentDao departmentDao = new DepartmentDaoImpl();


    @Override
    public List<Lector> getLectorsByDepartmentName(String departmentName) {
        List<Lector> lectors = null;
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Department department = departmentDao.getDepartmentByName(departmentName);
            Integer id = department.getId();
            String query = "select * from departments_lectors\n" +
                    "inner join lectors on departments_lectors.lectors_id = lectors.id\n" +
                    "where departments_lectors.departments_id = :id";
            lectors = session.createNativeQuery(query, Lector.class).setParameter("id", id).getResultList();
        } finally {
            if (transaction != null) transaction.commit();
        }
        return lectors;
    }

    @Override
    public BigDecimal getAverageSalaryByDepartmentName(String departmentName) {
        List<Lector> lectors = getLectorsByDepartmentName(departmentName);
        BigDecimal sum = lectors.stream().map(Lector::getSalary).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal result = sum.divide(new BigDecimal(lectors.size()), 2, RoundingMode.HALF_EVEN);
        return result;
    }
}
