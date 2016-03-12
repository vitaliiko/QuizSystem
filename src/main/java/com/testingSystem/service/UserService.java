package com.testingSystem.service;

import com.testingSystem.entity.User;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends EntityService<User, Long> {

    User getByEmail(String email) throws HibernateException;

    void addAttempt(Long userId) throws HibernateException;
}
