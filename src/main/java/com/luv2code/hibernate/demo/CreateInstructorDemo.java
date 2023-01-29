package com.luv2code.hibernate.demo;

import com.entity.Course;
import com.entity.Instructor;
import com.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateInstructorDemo {
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
            //create the objects
            Instructor tempInstructor = new Instructor("Whatever", "Surname", "whatever@company.com");

            InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.whatever.com", "Love to make knit!!!");

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
            //add clean up code
            session.close();
            factory.close();
        }


    }
}
