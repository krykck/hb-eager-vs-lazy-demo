package com.luv2code.hibernate.demo;

import com.entity.Instructor;
import com.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteDemo {
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

            //get instructor by primary key / id
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);

            System.out.println("Found instructors: " + tempInstructor);

            //delete the instructors
            if(tempInstructor != null) {
                System.out.println("Deleting: " + tempInstructor);

                //Note: will ALSO delete associated "details" object
                //because of CascadeType.ALL
                session.remove(tempInstructor);
            }



            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }

        finally {
            factory.close();
        }


    }
}
