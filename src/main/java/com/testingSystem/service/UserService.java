package com.testingSystem.service;

import com.testingSystem.entity.User;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends EntityService<User, Long> {

    List<User> getFirst(String orderParameter, int greaterThan, int limit);

    User getByEmail(String email) throws HibernateException;

    void addAttempt(Long userId) throws HibernateException;
}
