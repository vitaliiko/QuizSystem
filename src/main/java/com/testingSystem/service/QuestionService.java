package com.testingSystem.service;

import com.testingSystem.entity.Answer;
import com.testingSystem.entity.Question;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService extends EntityService<Question, Long> {

    void setAnswers(Long questionId, List<Answer> answers) throws HibernateException;

    void setText(Long questionId, String text) throws HibernateException;
}
