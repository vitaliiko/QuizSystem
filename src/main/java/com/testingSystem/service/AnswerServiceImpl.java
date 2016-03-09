package com.testingSystem.service;

import com.testingSystem.dao.AnswerDao;
import com.testingSystem.entity.Answer;
import com.testingSystem.util.HibernateSessionUtil;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

    @Autowired private AnswerDao answerDao;
    @Autowired private HibernateSessionUtil sessionUtil;

    @Override
    public List<Answer> getAll(String orderParameter) throws HibernateException {
        List<Answer> answers = answerDao.getAll(orderParameter);
        return answers;
    }

    @Override
    public Answer getById(Long id) throws HibernateException {
        Answer answer = answerDao.getById(id);
        return answer;
    }

    @Override
    public Answer get(String propertyName, Object value) throws HibernateException {
        Answer answer = answerDao.get(propertyName, value);
        return answer;
    }

    @Override
    public Long save(Answer entity) throws HibernateException {
        Long generatedId = answerDao.save(entity);
        return generatedId;
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
