package com.testingSystem.service;

import com.testingSystem.dao.UserDao;
import com.testingSystem.entity.Test;
import com.testingSystem.entity.User;
import com.testingSystem.util.HibernateEntityInitializer;
import com.testingSystem.util.HibernateSessionUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class UserServiceImpl implements UserService {

    @Autowired private UserDao userDao;
    @Autowired private HibernateSessionUtil sessionUtil;
    @Autowired private HibernateEntityInitializer entityInitializer;

    @Override
    public List<User> getAll(String orderParameter) throws HibernateException {
        return userDao.getAll(orderParameter);
    }

    @Override
    public User getById(Long id) throws HibernateException {
        return userDao.getById(id);
    }

    @Override
    public User get(String propertyName, Object value) throws HibernateException {
        return userDao.get(propertyName, value);
    }

    @Override
    public User getByEmail(String email) throws HibernateException {
        return userDao.get("email", email);
    }

    @Override
    public Long save(User entity) throws HibernateException {
        return userDao.save(entity);
    }

    @Override
    public void update(User entity) throws HibernateException {
        userDao.update(entity);
    }

    @Override
    public void delete(User entity) throws HibernateException {
        userDao.delete(entity);
    }

    @Override
    public void addTest(Long userId, Test test) throws HibernateException {
        userDao.addTest(userId, test);
    }

    @Override
    public void addTest(String email, Test test) throws HibernateException {
        User user = userDao.get("email", email);
        Hibernate.initialize(user.getTests());
        user.getTests().add(test);
        userDao.update(user);
    }

    @Override
    public List<Test> getTests(String userEmail) throws HibernateException {
        User user = userDao.get("email", userEmail);
        Hibernate.initialize(user.getTests());
        return user.getTests();
    }

    @Override
    public List<Test> getTests(Long userId) throws HibernateException {
        User user = userDao.getById(userId);
        Hibernate.initialize(user.getTests());
        return user.getTests();
    }
}
