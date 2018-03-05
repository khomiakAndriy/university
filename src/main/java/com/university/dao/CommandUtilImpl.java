package com.university.dao;

import com.university.entity.Degree;
import com.university.entity.Department;
import com.university.entity.Lector;
import com.university.util.DataBaseUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CommandUtilImpl implements CommandUtil {
    private static CommandUtilImpl lectorDao = new CommandUtilImpl();
    private static SessionFactory sessionFactory = DataBaseUtil.getSessionFactory();

    private CommandUtilImpl() {
    }

    public static CommandUtilImpl getLectorDaoImpl() {
        return lectorDao;
    }


    @Override
    public List<Lector> getLectorsByDepartmentName(String departmentName) {
        List<Lector> lectors = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Department department = getDepartmentByName(departmentName);
            if (department == null) {
                return null;
            }
            Integer id = department.getId();

            String query = "SELECT * FROM departments_lectors\n" +
                    "INNER JOIN lectors ON departments_lectors.lectors_id = lectors.id\n" +
                    "WHERE departments_lectors.departments_id = :id";
            lectors = session.createNativeQuery(query, Lector.class).setParameter("id", id).getResultList();
            transaction.commit();
        } finally {
            if (session != null) session.close();
        }
        return lectors;
    }

    @Override
    public void showAverageSalaryByDepartmentName(String departmentName) {
        List<Lector> lectors = getLectorsByDepartmentName(departmentName);
        if (lectors != null) {
            BigDecimal sum = lectors.stream().map(Lector::getSalary).reduce(BigDecimal.ZERO, BigDecimal::add);
            System.out.println(sum.divide(new BigDecimal(lectors.size()), 2, RoundingMode.HALF_EVEN));
        }
    }

    @Override
    public void showLectorsCountByDepartmentName(String departmentName) {
        List<Lector> lectors = getLectorsByDepartmentName(departmentName);
        if (lectors != null) {
            System.out.println(lectors.size());
        }
    }

    @Override
    public void searchByKeyword(String keyword) {
        List<String> lectorNames = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            lectorNames = session.createNamedQuery(Lector.SEARCH_LECTORS_KEYWORD, String.class).setParameter("keyword", "%" + keyword + "%").getResultList();
            transaction.commit();
        } finally {
            if (session != null) session.close();
        }
        String result = lectorNames.stream().map(String::toString).collect(Collectors.joining(", "));
        System.out.println(result);
    }

    @Override
    public void showDepartmentChief(String departmentName) {
        Department department = getDepartmentByName(departmentName);
        if (department != null) {
            System.out.println(getDepartmentByName(departmentName).getChief());
        }
    }

    @Override
    public Department getDepartmentByName(String departmentName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Department department = null;
        try {
            transaction = session.beginTransaction();
            department = session.createNamedQuery(Department.GET_DEPARTMENT_BY_NAME, Department.class).setParameter("name", departmentName).getSingleResult();
            transaction.commit();
        } catch (NoResultException exception) {
            System.out.println("Please write correct department name");
        } finally {
            if (session != null) session.close();
        }
        return department;
    }

    @Override
    public void showDepartmentStatisticByDepName(String departmentName) {
        List<Lector> lectors = lectorDao.getLectorsByDepartmentName(departmentName);
        if (lectors != null) {
            Set<Degree> degrees = lectors.stream().map(Lector::getDegree).collect(Collectors.toSet());
            for (Degree degree : degrees) {
                System.out.println(degree.getName() + " - " + countLectorsByDegree(lectors, degree));
            }
        }
    }

    private int countLectorsByDegree(List<Lector> lectors, Degree degree) {
        return (int) lectors.stream().filter(l -> l.getDegree().equals(degree)).count();
    }
}
