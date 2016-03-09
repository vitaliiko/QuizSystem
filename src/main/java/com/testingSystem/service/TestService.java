package com.testingSystem.service;

import com.testingSystem.entity.Question;
import com.testingSystem.entity.Test;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestService extends EntityService<Test, Long> {

    void setQuestions(Long testId, List<Question> questions) throws HibernateException;
}
