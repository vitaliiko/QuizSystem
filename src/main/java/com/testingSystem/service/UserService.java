package com.testingSystem.service;

import com.testingSystem.dao.UserDao;
import com.testingSystem.entity.User;
import com.testingSystem.util.DataBaseException;
import com.testingSystem.util.HibernateSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements EntityService<User, Long> {

    @Autowired private UserDao userDao;
    @Autowired private HibernateSessionUtil sessionUtil;

    @Override
    public List<User> getAll(String orderParameter) throws DataBaseException {
        sessionUtil.openCurrentSession();
        List<User> users = userDao.getAll(orderParameter);
        sessionUtil.closeCurrentSession();
        return users;
    }

    @Override
    public User getById(Long id) throws DataBaseException {
        sessionUtil.openCurrentSession();
        User user = userDao.getById(id);
        sessionUtil.closeCurrentSession();
        return user;
    }

    @Override
    public User get(String propertyName, Object value) throws DataBaseException {
        sessionUtil.openCurrentSession();
        User user = userDao.get(propertyName, value);
        sessionUtil.closeCurrentSession();
        return user;
    }

    @Override
    public Long save(User entity) throws DataBaseException {
        sessionUtil.openCurrentSessionAndBeginTransaction();
        Long generatedId = userDao.save(entity);
        sessionUtil.commitAndcloseCurrentSession();
        return generatedId;
    }

    @Override
    public void update(User entity) throws DataBaseException {
        sessionUtil.openCurrentSessionAndBeginTransaction();
        userDao.update(entity);
        sessionUtil.commitAndcloseCurrentSession();
    }

    @Override
    public void delete(User entity) throws DataBaseException {
        sessionUtil.openCurrentSessionAndBeginTransaction();
        userDao.delete(entity);
        sessionUtil.commitAndcloseCurrentSession();
    }
}
