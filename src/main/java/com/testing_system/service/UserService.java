package com.testing_system.service;

import com.testing_system.entity.User;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends EntityService<User, Long> {

    List<User> getFirst(String orderParameter, float greaterThan, int limit);

    User getByEmail(String email) throws HibernateException;

    void addAttempt(Long userId) throws HibernateException;
}
