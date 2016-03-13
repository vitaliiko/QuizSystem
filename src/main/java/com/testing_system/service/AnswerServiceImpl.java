package com.testing_system.service;

import com.testing_system.dao.AnswerDao;
import com.testing_system.entity.Answer;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

    @Autowired private AnswerDao answerDao;

    @Override
    public List<Answer> getAll(String orderParameter) throws HibernateException {
        return answerDao.getAll(orderParameter);
    }

    @Override
    public Answer getById(Integer id) throws HibernateException {
        return answerDao.getById(id);
    }

    @Override
    public Answer get(String propertyName, Object value) throws HibernateException {
        return answerDao.get(propertyName, value);
    }

    @Override
    public Integer save(Answer entity) throws HibernateException {
        return answerDao.save(entity);
    }

    @Override
    public void update(Answer entity) throws HibernateException {
        answerDao.update(entity);
    }

    @Override
    public void delete(Answer entity) throws HibernateException {
        answerDao.delete(entity);
    }
}
