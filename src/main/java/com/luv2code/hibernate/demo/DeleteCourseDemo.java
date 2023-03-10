package com.luv2code.hibernate.demo;

import com.entity.Course;
import com.entity.Instructor;
import com.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteCourseDemo {
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

            //get a course
            int theId = 10;
            Course tempCourse = session.get(Course.class, theId);
            session.remove(tempCourse);

            //delete course

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }

        finally {
            //add clean up code
            session.close();
            factory.close();
        }


    }
}
