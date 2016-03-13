package com.testingSystem.service;

import com.testingSystem.dao.QuestionDao;
import com.testingSystem.entity.Answer;
import com.testingSystem.entity.Question;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    @Autowired private QuestionDao questionDao;

    @Override
    public List<Question> getAll(String orderParameter) throws HibernateException {
        return questionDao.getAll(orderParameter);
    }

    @Override
    public Question getById(Integer id) throws HibernateException {
        return questionDao.getById(id);
    }

    @Override
    public Question get(String propertyName, Object value) throws HibernateException {
        return questionDao.get(propertyName, value);
    }

    @Override
    public Integer save(Question entity) throws HibernateException {
        return questionDao.save(entity);
    }

    @Override
    public void update(Question entity) throws HibernateException {
        questionDao.update(entity);
    }

    @Override
    public void delete(Question entity) throws HibernateException {
        questionDao.delete(entity);
    }

    @Override
    public void setAnswers(Integer questionId, List<Answer> answers) throws HibernateException {
        questionDao.setAnswers(questionId, answers);
    }

    @Override
    public void setText(Integer questionId, String text) throws HibernateException {
        questionDao.setText(questionId, text);
    }

    @Override
    public Long getQuestionCount() throws HibernateException {
        return questionDao.getQuestionCount();
    }
}
