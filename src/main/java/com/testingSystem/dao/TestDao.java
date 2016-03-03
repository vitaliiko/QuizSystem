package com.testingSystem.dao;

import com.testingSystem.entity.Question;
import com.testingSystem.entity.Test;
import com.testingSystem.entity.User;
import com.testingSystem.util.HibernateSessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("unchecked")
public class TestDao implements EntityDao<Test, Long> {

    @Autowired
    private HibernateSessionUtil sessionUtil;
    private Class<Test> clazz = Test.class;

    @Override
    public List<Test> getAll(String orderParameter) throws HibernateException {
        return (List<Test>) sessionUtil.getCurrentSession()
                .createCriteria(clazz)
                .addOrder(Order.asc(orderParameter))
                .list();
    }

    @Override
    public Test getById(Long id) throws HibernateException {
        return (Test) sessionUtil.getCurrentSession()
                .get(clazz, id);
    }

    @Override
    public Test get(String propertyName, Object value) throws HibernateException {
        List<Test> list = (List<Test>) sessionUtil.getCurrentSession()
                .createCriteria(clazz)
                .add(Restrictions.eq(propertyName, value))
                .list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Long save(Test entity) throws HibernateException {
        return (Long) sessionUtil.getCurrentSession().save(entity);
    }

    @Override
    public void update(Test entity) throws HibernateException {
        sessionUtil.getCurrentSession().update(entity);
    }

    @Override
    public void delete(Test entity) throws HibernateException {
        sessionUtil.getCurrentSession().delete(entity);
    }

    public void setQuestions(Long testId, List<Question> questions) throws HibernateException {
        Test test = (Test) sessionUtil.getCurrentSession().get(Test.class, testId);
        test.setQuestions(questions);
        sessionUtil.getCurrentSession().update(test);
    }
}
