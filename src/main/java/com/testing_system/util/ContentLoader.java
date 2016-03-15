package com.testing_system.util;

import com.testing_system.entity.Answer;
import com.testing_system.entity.Question;
import com.testing_system.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ContentLoader {

    @Autowired private String contents;
    @Autowired private QuestionService questionService;

    public void loadContent() {
        String questionText = null;
        List<Answer> answers = new ArrayList<>();
        Answer rightAnswer = null;
        for (String s : contents.split("\r\n")) {
            if (!s.equals("***")) {
                if (questionText == null) {
                    questionText = s;
                } else {
                    Answer answer = new Answer(s);
                    if (s.startsWith("*")) {
                        answer.setText(s.replace("*", ""));
                        rightAnswer = answer;
                    }
                    answers.add(answer);
                }
            } else {
                Question question = new Question(questionText, answers, rightAnswer);
                questionService.save(question);
                questionText = null;
                answers.clear();
            }
        }
    }
}
