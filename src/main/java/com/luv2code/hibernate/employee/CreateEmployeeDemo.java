package com.luv2code.hibernate.employee;

import com.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateEmployeeDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernateEmployee.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        //create session object
        Session session = factory.getCurrentSession();

        //save the employee object to database

        try{

            //create employee object
            Employee employee = new Employee("Koray", "Kocak", "Bites");

            //begin transaction
            session.beginTransaction();

            //save the employee object to database
            session.persist(employee);

            //commit transaction
            session.getTransaction().commit();

        } finally {
            factory.close();
        }

    }

}
