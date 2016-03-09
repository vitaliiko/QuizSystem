package com.testingSystem.service;

import com.testingSystem.entity.Test;
import com.testingSystem.entity.User;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends EntityService<User, Long> {

    User getByEmail(String email) throws HibernateException;

    void addTest(String email, Test test) throws HibernateException;

    void addTest(Long userId, Test test) throws HibernateException;

    List<Test> getTests(String userEmail) throws HibernateException;

    List<Test> getTests(Long userId) throws HibernateException;
}
