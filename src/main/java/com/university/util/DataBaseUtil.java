package com.university.util;

import com.university.entity.Degree;
import com.university.entity.Department;
import com.university.entity.Lector;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DataBaseUtil {
    public static SessionFactory getSessionFactory() {
        return new Configuration()
                .configure()
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Lector.class)
                .addAnnotatedClass(Degree.class)
                .buildSessionFactory();
    }
}
