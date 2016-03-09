package com.testingSystem.service;

import com.testingSystem.dao.TestDao;
import com.testingSystem.entity.Question;
import com.testingSystem.entity.Test;
import com.testingSystem.util.HibernateSessionUtil;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TestServiceImpl implements TestService {

    @Autowired private TestDao testDao;
    @Autowired private HibernateSessionUtil sessionUtil;

    @Override
    public List<Test> getAll(String orderParameter) throws HibernateException {
        List<Test> tests = testDao.getAll(orderParameter);
        return tests;
    }

    @Override
    public Test getById(Long id) throws HibernateException {
        Test test = testDao.getById(id);
        return test;
    }

    @Override
    public Test get(String propertyName, Object value) throws HibernateException {
        Test test = testDao.get(propertyName, value);
        return test;
    }

    @Override
    public Long save(Test entity) throws HibernateException {
        Long generatedId = testDao.save(entity);
        return generatedId;
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
    public void setQuestions(Long testId, List<Question> questions) throws HibernateException {
        testDao.setQuestions(testId, questions);
    }
}
