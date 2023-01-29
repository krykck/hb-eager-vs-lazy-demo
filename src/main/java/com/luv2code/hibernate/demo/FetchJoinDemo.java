package com.luv2code.hibernate.demo;

import com.entity.Course;
import com.entity.Instructor;
import com.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class FetchJoinDemo {
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



            //option 2 hibernate query with HQL

            //get the instructor from db

            int theId = 1;
            Query<Instructor> query = session.createQuery("select i from Instructor i "
                                            + "JOIN FETCH i.courses "
                                            + "where i.id=:theInstructorId",
                                Instructor.class);


            //set parameter on query
            query.setParameter("theInstructorId", theId);

            //execute query and get instructor
            Instructor tempInstructor = query.getSingleResult();


            System.out.println("Instructor: " + tempInstructor);


            session.getTransaction().commit();
            session.close();


            //commit transaction
            System.out.println(tempInstructor.getCourses());

            System.out.println("luv2code: Done!");
        }

        finally {
            //add clean up code
            session.close();
            factory.close();
        }


    }
}
