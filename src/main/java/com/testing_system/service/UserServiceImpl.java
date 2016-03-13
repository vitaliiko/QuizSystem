package com.testing_system.service;

import com.testing_system.dao.UserDao;
import com.testing_system.entity.User;
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
    public List<User> getFirst(String orderParameter, float greaterThan, int limit) {
        return userDao.getFirst(orderParameter, greaterThan, limit);
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
