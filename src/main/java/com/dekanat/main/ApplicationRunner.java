package com.dekanat.main;

import com.dekanat.entities.Student;
import com.dekanat.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ApplicationRunner {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactory = HibernateUtil.getSessionFactory();

        ApplicationRunner runner = new ApplicationRunner();

        System.out.println("Adding developer's records to the DB");
        /**
         *  Adding developer's records to the database (DB)
         */
        //runner.addStudent("One", "Person");
       // runner.addStudent("Another", "Person");

        System.out.println("List of students");
        /**
         * List developers
         */
        List students = runner.listStudents();
//        for (Student student : students) {
//            System.out.println(student);
//        }
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i));
        }

        System.out.println("===================================");
        System.out.println("Removing and updating some students ");
        /**
         * Update and Remove developers
         */
        runner.updateStudent(4, "Other");
        runner.removeStudent(8);

        System.out.println("Final list of students");
        /**
         * List developers
         */
        students = runner.listStudents();
//        for (Student student : students) {
//            System.out.println(student);
//        }
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i));
        }
        System.out.println("===================================");

    }

    private void addStudent(String firstName, String lastName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Student student = new Student(firstName, lastName);
        session.save(student);
        transaction.commit();
        session.close();
    }

    private List listStudents() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List students = session.createQuery("FROM Student").list();

        transaction.commit();
        session.close();
        return students;
    }

    private void updateStudent(long id, String firstName) {
        Session session = sessionFactory.openSession();
        Transaction transaction =  session.beginTransaction();
        Student student = session.get(Student.class, id);
        student.setFirstName(firstName);
        session.update(student);
        transaction.commit();
        session.close();
    }

    private void removeStudent(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class, id);
        session.delete(student);
        transaction.commit();
        session.close();
    }

}