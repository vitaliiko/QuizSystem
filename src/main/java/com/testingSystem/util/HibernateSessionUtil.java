package com.testingSystem.util;

import org.hibernate.HibernateException;
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

    public Session getCurrentSession() throws HibernateException {
        return currentSession;
    }

    public Session openCurrentSession() throws HibernateException {
        currentSession = sessionFactory.openSession();
        return currentSession;
    }

    public Session openCurrentSessionAndBeginTransaction() throws HibernateException {
        currentSession = sessionFactory.openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() throws HibernateException {
        currentSession.close();
    }

    public void commitAndCloseCurrentSession() throws HibernateException {
        currentTransaction.commit();
        currentSession.close();
    }
}
