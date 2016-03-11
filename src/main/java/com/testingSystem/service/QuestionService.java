package com.testingSystem.service;

import com.testingSystem.entity.Answer;
import com.testingSystem.entity.Question;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService extends EntityService<Question, Integer> {

    void setAnswers(Integer questionId, List<Answer> answers) throws HibernateException;

    void setText(Integer questionId, String text) throws HibernateException;

    Long getQuestionCount() throws HibernateException;
}
