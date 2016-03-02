package com.testingSystem.service;

import com.testingSystem.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired private TestDao testDao;
}
