package com.luv2code.hibernate.demo;

import com.entity.Instructor;
import com.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteInstructorDetailDemo {
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

            //start a transaction
            session.beginTransaction();

           //get the instructor detail object
            int theId = 2;

            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);

            //print the instructor detail
            System.out.println("tempInstructor detail: " + tempInstructorDetail);
            //print the associated instructor
            System.out.println(tempInstructorDetail.getInstructor());

            //remove the associated object reference
            //break bi-directional link
            tempInstructorDetail.getInstructor().setInstructorDetail(null);

            session.remove(tempInstructorDetail);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

        finally {

            //handle connection leak issue
            session.close();
            factory.close();
        }


    }
}
