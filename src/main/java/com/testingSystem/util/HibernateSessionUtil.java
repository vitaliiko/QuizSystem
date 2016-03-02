package com.testingSystem.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HibernateSessionUtil {

    @Autowired
    private SessionFactory sessionFactory;
    private Session currentSession;
    private Transaction currentTransaction;

    public Session getCurrentSession() {
        return currentSession;
    }

    public Session openCurrentSession() {
        currentSession = sessionFactory.openSession();
        return currentSession;
    }

    public Session openCurrentSessionAndBeginTransaction() {
        currentSession = sessionFactory.openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void commitAndcloseCurrentSession() {
        currentTransaction.commit();
        currentSession.close();
    }
}
