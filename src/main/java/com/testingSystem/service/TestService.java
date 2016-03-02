package com.testingSystem.service;

import com.testingSystem.dao.TestDao;
import com.testingSystem.entity.Question;
import com.testingSystem.entity.Test;
import com.testingSystem.util.HibernateSessionUtil;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService implements EntityService<Test, Long> {

    @Autowired private TestDao testDao;
    @Autowired private HibernateSessionUtil sessionUtil;

    @Override
    public List<Test> getAll(String orderParameter) throws HibernateException {
        sessionUtil.openCurrentSession();
        List<Test> tests = testDao.getAll(orderParameter);
        sessionUtil.closeCurrentSession();
        return tests;
    }

    @Override
    public Test getById(Long id) throws HibernateException {
        sessionUtil.openCurrentSession();
        Test test = testDao.getById(id);
        sessionUtil.closeCurrentSession();
        return test;
    }

    @Override
    public Test get(String propertyName, Object value) throws HibernateException {
        sessionUtil.openCurrentSession();
        Test test = testDao.get(propertyName, value);
        sessionUtil.closeCurrentSession();
        return test;
    }

    @Override
    public Long save(Test entity) throws HibernateException {
        sessionUtil.openCurrentSessionAndBeginTransaction();
        Long generatedId = testDao.save(entity);
        sessionUtil.commitAndCloseCurrentSession();
        return generatedId;
    }

    @Override
    public void update(Test entity) throws HibernateException {
        sessionUtil.openCurrentSessionAndBeginTransaction();
        testDao.update(entity);
        sessionUtil.commitAndCloseCurrentSession();
    }

    @Override
    public void delete(Test entity) throws HibernateException {
        sessionUtil.openCurrentSessionAndBeginTransaction();
        testDao.delete(entity);
        sessionUtil.commitAndCloseCurrentSession();
    }

    public void setQuestions(Long testId, List<Question> questions) throws HibernateException {
        testDao.setQuestions(testId, questions);
    }
}
