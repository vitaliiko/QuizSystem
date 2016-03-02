package com.testingSystem.service;

import com.testingSystem.dao.AnswerDao;
import com.testingSystem.entity.Answer;
import com.testingSystem.util.HibernateSessionUtil;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService implements EntityService<Answer, Long> {

    @Autowired private AnswerDao answerDao;
    @Autowired private HibernateSessionUtil sessionUtil;

    @Override
    public List<Answer> getAll(String orderParameter) throws HibernateException {
        sessionUtil.openCurrentSession();
        List<Answer> answers = answerDao.getAll(orderParameter);
        sessionUtil.closeCurrentSession();
        return answers;
    }

    @Override
    public Answer getById(Long id) throws HibernateException {
        sessionUtil.openCurrentSession();
        Answer answer = answerDao.getById(id);
        sessionUtil.closeCurrentSession();
        return answer;
    }

    @Override
    public Answer get(String propertyName, Object value) throws HibernateException {
        sessionUtil.openCurrentSession();
        Answer answer = answerDao.get(propertyName, value);
        sessionUtil.closeCurrentSession();
        return answer;
    }

    @Override
    public Long save(Answer entity) throws HibernateException {
        sessionUtil.openCurrentSessionAndBeginTransaction();
        Long generatedId = answerDao.save(entity);
        sessionUtil.commitAndCloseCurrentSession();
        return generatedId;
    }

    @Override
    public void update(Answer entity) throws HibernateException {
        sessionUtil.openCurrentSessionAndBeginTransaction();
        answerDao.update(entity);
        sessionUtil.commitAndCloseCurrentSession();
    }

    @Override
    public void delete(Answer entity) throws HibernateException {
        sessionUtil.openCurrentSessionAndBeginTransaction();
        answerDao.delete(entity);
        sessionUtil.commitAndCloseCurrentSession();
    }
}
