package com.luv2code.hibernate.employee;

import com.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadEmployeeDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernateEmployee.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        //get current session
        Session session = factory.getCurrentSession();

        try{
            //create employee object
            Employee employee = new Employee("Isilay", "Pamuk", "Tesla");

            //start transaction
            session.beginTransaction();

            //save the employee object to the database
            session.persist(employee);

            //commit transaction
            session.getTransaction().commit();

            //get a new session and start a new transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //get the employee based on the id: primary key
            employee = session.get(Employee.class, employee.getId());
            System.out.println(employee);

            //commit transaction
            session.getTransaction().commit();
        }

        finally {
            //close factory
            factory.close();
        }

    }
}
