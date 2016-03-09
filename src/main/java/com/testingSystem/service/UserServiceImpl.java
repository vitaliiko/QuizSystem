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
@SuppressWarnings("unchecked")
public class UserServiceImpl implements UserService {

    @Autowired private UserDao userDao;
    @Autowired private HibernateSessionUtil sessionUtil;
    @Autowired private HibernateEntityInitializer entityInitializer;

    @Override
    @Transactional
    public List<User> getAll(String orderParameter) throws HibernateException {
        List<User> users = userDao.getAll(orderParameter);
        return users;
    }

    @Override
    @Transactional
    public User getById(Long id) throws HibernateException {
        User user = userDao.getById(id);
        return user;
    }

    @Override
    @Transactional
    public User get(String propertyName, Object value) throws HibernateException {
        User user = userDao.get(propertyName, value);
        return user;
    }

    @Override
    @Transactional
    public User getByEmail(String email) throws HibernateException {
        User user = userDao.get("email", email);
        return user;
    }

    @Override
    @Transactional
    public Long save(User entity) throws HibernateException {
        Long generatedId = userDao.save(entity);
        return generatedId;
    }

    @Override
    @Transactional
    public void update(User entity) throws HibernateException {
        userDao.update(entity);
    }

    @Override
    @Transactional
    public void delete(User entity) throws HibernateException {
        userDao.delete(entity);
    }

    @Override
    @Transactional
    public void addTest(Long userId, Test test) throws HibernateException {
        userDao.addTest(userId, test);
    }

    @Override
    @Transactional
    public void addTest(String email, Test test) throws HibernateException {
        User user = userDao.get("email", email);
        Hibernate.initialize(user.getTests());
        user.getTests().add(test);
        userDao.update(user);
    }

    @Override
    @Transactional
    public List<Test> getTests(String userEmail) throws HibernateException {
        User user = userDao.get("email", userEmail);
        Hibernate.initialize(user.getTests());
        return user.getTests();
    }

    @Override
    @Transactional
    public List<Test> getTests(Long userId) throws HibernateException {
        User user = userDao.getById(userId);
        Hibernate.initialize(user.getTests());
        return user.getTests();
    }
}
