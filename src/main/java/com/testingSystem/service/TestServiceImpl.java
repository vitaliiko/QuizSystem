package com.testingSystem.service;

import com.testingSystem.dao.TestDao;
import com.testingSystem.entity.Question;
import com.testingSystem.entity.Test;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TestServiceImpl implements TestService {

    @Autowired private TestDao testDao;

    @Override
    public List<Test> getAll(String orderParameter) throws HibernateException {
        return testDao.getAll(orderParameter);
    }

    @Override
    public Test getById(Integer id) throws HibernateException {
        return testDao.getById(id);
    }

    @Override
    public Test get(String propertyName, Object value) throws HibernateException {
        return testDao.get(propertyName, value);
    }

    @Override
    public Integer save(Test entity) throws HibernateException {
        return testDao.save(entity);
    }

    @Override
    public void update(Test entity) throws HibernateException {
        testDao.update(entity);
    }

    @Override
    public void delete(Test entity) throws HibernateException {
        testDao.delete(entity);
    }

    @Override
    public void setQuestions(Integer testId, List<Question> questions) throws HibernateException {
        testDao.setQuestions(testId, questions);
    }
}
