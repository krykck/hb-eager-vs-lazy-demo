package com.luv2code.hibernate.demo;

import com.entity.Instructor;
import com.entity.InstructorDetail;
import com.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;


public class CreateDemo {
    public static void main(String[] args) {
        //Create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();
        //Create session
        Session session = factory.getCurrentSession();

        try {
            //create the objects
            Instructor tempInstructor = new Instructor("Koray", "Kocak", "koray.kocak@company.com");

            InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.koraykocak.com", "Love to make money!!!");

            //associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            //start a transaction
            session.beginTransaction();

            //save the instructor
            //Note: this will ALSO save the details object
            //because of CascadeType.ALL
            System.out.println("Saving instructor" + tempInstructor);
            session.persist(tempInstructor);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }

        finally {
            factory.close();
        }


    }
}
