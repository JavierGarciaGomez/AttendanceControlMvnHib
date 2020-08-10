package com.JGG.AttendanceControl.Entity;

import com.sun.xml.bind.v2.TODO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnection {

    private final SessionFactory factory;
    private Session currentSession;

    public HibernateConnection() {
        factory = new Configuration().configure().addAnnotatedClass(User.class).buildSessionFactory();
        Session session = factory.getCurrentSession();
    }

    public SessionFactory getFactory() {
        return factory;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public void closeSession(){
        currentSession.close();
    }

    //TODO
    /*session.beginTransaction();
        User user = session.get(User.class, 1);
        System.out.println(user);
        session.getTransaction().commit();

        System.out.println(user);
        */


}
