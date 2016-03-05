package com.testingSystem.dao;

import com.testingSystem.entity.Test;
import com.testingSystem.entity.User;
import com.testingSystem.util.HibernateSessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@SuppressWarnings("unchecked")
public class UserDao implements EntityDao<User, Long> {

    @Autowired
    private HibernateSessionUtil sessionUtil;
    private Class<User> clazz = User.class;

    @Override
    public List<User> getAll(String orderParameter) throws HibernateException {
        return (List<User>) sessionUtil.getCurrentSession()
                .createCriteria(clazz)
                .addOrder(Order.asc(orderParameter))
                .list();
    }

    @Override
    public User getById(Long id) throws HibernateException {
        return (User) sessionUtil.getCurrentSession()
                .get(clazz, id);
    }

    @Override
    public User get(String propertyName, Object value) throws HibernateException {
        List<User> list = (List<User>) sessionUtil.getCurrentSession()
                .createCriteria(clazz)
                .add(Restrictions.eq(propertyName, value))
                .list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Long save(User entity) throws HibernateException {
        return (Long) sessionUtil.getCurrentSession().save(entity);
    }

    @Override
    public void update(User entity) throws HibernateException {
        sessionUtil.getCurrentSession().update(entity);
    }

    @Override
    public void delete(User entity) throws HibernateException {
        sessionUtil.getCurrentSession().delete(entity);
    }

    public void addTest(Long userId, Test test) throws HibernateException {
        User user = getById(userId);
        user.getTests().add(test);
        sessionUtil.getCurrentSession().update(user);
    }


}
