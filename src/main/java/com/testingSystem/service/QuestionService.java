package com.testingSystem.service;

import com.testingSystem.dao.QuestionDao;
import com.testingSystem.entity.Answer;
import com.testingSystem.entity.Question;
import com.testingSystem.util.HibernateSessionUtil;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService implements EntityService<Question, Long> {

    @Autowired private QuestionDao questionDao;
    @Autowired private HibernateSessionUtil sessionUtil;

    @Override
    public List<Question> getAll(String orderParameter) throws HibernateException {
        sessionUtil.openCurrentSession();
        List<Question> questions = questionDao.getAll(orderParameter);
        sessionUtil.closeCurrentSession();
        return questions;
    }

    @Override
    public Question getById(Long id) throws HibernateException {
        sessionUtil.openCurrentSession();
        Question question = questionDao.getById(id);
        sessionUtil.closeCurrentSession();
        return question;
    }

    @Override
    public Question get(String propertyName, Object value) throws HibernateException {
        sessionUtil.openCurrentSession();
        Question question = questionDao.get(propertyName, value);
        sessionUtil.closeCurrentSession();
        return question;
    }

    @Override
    public Long save(Question entity) throws HibernateException {
        sessionUtil.openCurrentSessionAndBeginTransaction();
        Long generatedId = questionDao.save(entity);
        sessionUtil.commitAndCloseCurrentSession();
        return generatedId;
    }

    @Override
    public void update(Question entity) throws HibernateException {
        sessionUtil.openCurrentSessionAndBeginTransaction();
        questionDao.update(entity);
        sessionUtil.commitAndCloseCurrentSession();
    }

    @Override
    public void delete(Question entity) throws HibernateException {
        sessionUtil.openCurrentSessionAndBeginTransaction();
        questionDao.delete(entity);
        sessionUtil.commitAndCloseCurrentSession();
    }

    public void setAnswers(Long questionId, List<Answer> answers) throws HibernateException {
        questionDao.setAnswers(questionId, answers);
    }

    public void setText(Long questionId, String text) throws HibernateException {
        questionDao.setText(questionId, text);
    }
}
