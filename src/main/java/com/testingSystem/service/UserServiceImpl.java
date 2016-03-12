package com.testingSystem.service;

import com.testingSystem.dao.UserDao;
import com.testingSystem.entity.User;
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
    public void addAttempt(Long userId) throws HibernateException {
        userDao.addAttempt(userId);
    }
}
