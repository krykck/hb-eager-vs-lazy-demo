package com.luv2code.hibernate.employee;

import com.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryEmployeeDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernateEmployee.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
            session.beginTransaction();

            List<Employee> employeeList = session.createQuery("from Employee", Employee.class).getResultList();
            displayEmployee(employeeList);

            employeeList = session.createQuery("from Employee e where e.lastName='Kocak'", Employee.class).getResultList();

            displayEmployee(employeeList);

            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }
    }

    private static void displayEmployee(List<Employee> employeeList) {
        for(Employee employee : employeeList) {
            System.out.println(employee);
        }
    }
}
