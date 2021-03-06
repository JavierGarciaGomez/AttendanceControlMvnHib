package com.JGG.AttendanceControl.Tests;

import com.JGG.AttendanceControl.Entity.HibernateConnection;
import com.JGG.AttendanceControl.Entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.sql.ResultSet;

public class TestMyHibernateConnectionClass {
    public static void main(String[] args) {
        HibernateConnection hibernateConnection = HibernateConnection.getInstance();
        Session currentSession = hibernateConnection.getSession();
        System.out.println(currentSession);
        User user = new User();
        user.getUsers();

        // get max ID
        currentSession.close();
        Session session= hibernateConnection.getSession();
        session.beginTransaction();
        Query query = session.createQuery("select MAX(id) from User");
        int maxId= (Integer) query.getSingleResult();
        session.close();
        System.out.println("The max id is "+maxId);




    }
}
