package com.testingSystem.service;

import com.testingSystem.dao.UserDao;
import com.testingSystem.entity.Test;
import com.testingSystem.entity.User;
import com.testingSystem.util.HibernateSessionUtil;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements EntityService<User, Long> {

    @Autowired private UserDao userDao;
    @Autowired private HibernateSessionUtil sessionUtil;

    @Override
    public List<User> getAll(String orderParameter) throws HibernateException {
        sessionUtil.openCurrentSession();
        List<User> users = userDao.getAll(orderParameter);
        sessionUtil.closeCurrentSession();
        return users;
    }

    @Override
    public User getById(Long id) throws HibernateException {
        sessionUtil.openCurrentSession();
        User user = userDao.getById(id);
        sessionUtil.closeCurrentSession();
        return user;
    }

    @Override
    public User get(String propertyName, Object value) throws HibernateException {
        sessionUtil.openCurrentSession();
        User user = userDao.get(propertyName, value);
        sessionUtil.closeCurrentSession();
        return user;
    }

    @Override
    public Long save(User entity) throws HibernateException {
        sessionUtil.openCurrentSessionAndBeginTransaction();
        Long generatedId = userDao.save(entity);
        sessionUtil.commitAndCloseCurrentSession();
        return generatedId;
    }

    @Override
    public void update(User entity) throws HibernateException {
        sessionUtil.openCurrentSessionAndBeginTransaction();
        userDao.update(entity);
        sessionUtil.commitAndCloseCurrentSession();
    }

    @Override
    public void delete(User entity) throws HibernateException {
        sessionUtil.openCurrentSessionAndBeginTransaction();
        userDao.delete(entity);
        sessionUtil.commitAndCloseCurrentSession();
    }

    public void addTest(Long userId, Test test) throws HibernateException {
        sessionUtil.openCurrentSessionAndBeginTransaction();
        userDao.addTest(userId, test);
        sessionUtil.commitAndCloseCurrentSession();
    }
}
