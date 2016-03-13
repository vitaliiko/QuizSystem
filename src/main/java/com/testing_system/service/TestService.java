package com.testing_system.service;

import com.testing_system.entity.Question;
import com.testing_system.entity.Test;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestService extends EntityService<Test, Integer> {

    void setQuestions(Integer testId, List<Question> questions) throws HibernateException;
}
