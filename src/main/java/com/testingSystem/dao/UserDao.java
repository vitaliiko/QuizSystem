package com.testingSystem.dao;

import com.testingSystem.entity.Test;
import com.testingSystem.entity.User;
import com.testingSystem.util.DataBaseException;
import com.testingSystem.util.HibernateSessionUtil;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@SuppressWarnings("unchecked")
public class UserDao implements EntityDao<User, Long> {

    @Autowired
    private HibernateSessionUtil sessionUtil;
    private Class<User> clazz = User.class;

    @Override
    public List<User> getAll(String orderParameter) throws DataBaseException {
        return (List<User>) sessionUtil.getCurrentSession()
                .createCriteria(clazz)
                .addOrder(Order.asc(orderParameter))
                .list();
    }

    @Override
    public User getById(Long id) throws DataBaseException {
        return (User) sessionUtil.getCurrentSession()
                .get(clazz, id);
    }

    @Override
    public User get(String propertyName, Object value) throws DataBaseException {
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
    public Long save(User entity) throws DataBaseException {
        return (Long) sessionUtil.getCurrentSession().save(entity);
    }

    @Override
    public void update(User entity) throws DataBaseException {
        sessionUtil.getCurrentSession().update(entity);
    }

    @Override
    public void delete(User entity) throws DataBaseException {
        sessionUtil.getCurrentSession().delete(entity);
    }

    public void setTest(Long userId, Test test) throws DataBaseException {
        User user = getById(userId);
        user.setTest(test);
        sessionUtil.getCurrentSession().update(user);
    }
}
