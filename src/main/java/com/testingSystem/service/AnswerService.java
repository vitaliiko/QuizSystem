package com.testingSystem.service;

import com.testingSystem.dao.AnswerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired private AnswerDao answerDao;
}
