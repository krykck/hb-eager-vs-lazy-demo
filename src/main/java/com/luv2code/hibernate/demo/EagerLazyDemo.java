package com.luv2code.hibernate.demo;

import com.entity.Course;
import com.entity.Instructor;
import com.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class EagerLazyDemo {
    public static void main(String[] args) {
        //Create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();
        //Create session
        Session session = factory.getCurrentSession();

        try {


            //start a transaction
            session.beginTransaction();

            //get the instructor from db
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);

            System.out.println("Instructor: " + tempInstructor);

            System.out.println("luv2code: Courses: " + tempInstructor.getCourses());
            session.getTransaction().commit();
            session.close();

            System.out.println("luv2code: Courses: " + tempInstructor.getCourses());
            //commit transaction

            System.out.println("luv2code: Done!");
        }

        finally {
            //add clean up code
            session.close();
            factory.close();
        }


    }
}
