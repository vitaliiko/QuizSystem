package com.testing_system.dao;

import com.testing_system.entity.Question;
import com.testing_system.entity.Test;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class TestDao implements EntityDao<Test, Integer> {

    @Autowired
    private SessionFactory sessionFactory;
    private Class<Test> clazz = Test.class;

    @Override
    public List<Test> getAll(String orderParameter) throws HibernateException {
        return (List<Test>) sessionFactory.getCurrentSession()
                .createCriteria(clazz)
                .addOrder(Order.asc(orderParameter))
                .list();
    }

    @Override
    public Test getById(Integer id) throws HibernateException {
        return (Test) sessionFactory.getCurrentSession()
                .get(clazz, id);
    }

    @Override
    public Test get(String propertyName, Object value) throws HibernateException {
        List<Test> list = (List<Test>) sessionFactory.getCurrentSession()
                .createCriteria(clazz)
                .add(Restrictions.eq(propertyName, value))
                .list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Integer save(Test entity) throws HibernateException {
        return (Integer) sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void update(Test entity) throws HibernateException {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public void delete(Test entity) throws HibernateException {
        sessionFactory.getCurrentSession().delete(entity);
    }

    public void setQuestions(Integer testId, List<Question> questions) throws HibernateException {
        Test test = (Test) sessionFactory.getCurrentSession().get(Test.class, testId);
        test.setQuestions(questions);
        sessionFactory.getCurrentSession().update(test);
    }
}
