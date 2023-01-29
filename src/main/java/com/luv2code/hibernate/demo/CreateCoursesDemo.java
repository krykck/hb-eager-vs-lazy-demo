package com.luv2code.hibernate.demo;

import com.entity.Course;
import com.entity.Instructor;
import com.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateCoursesDemo {
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

            //create courses
            Course tempCourse1 = new Course("Music");
            Course tempCourse2 = new Course("Sport");

            //add courses to instructor
            tempInstructor.add(tempCourse1);
            tempInstructor.add(tempCourse2);

            //save the courses
            session.persist(tempCourse1);
            session.persist(tempCourse2);
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
