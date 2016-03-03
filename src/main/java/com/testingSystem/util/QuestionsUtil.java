package com.testingSystem.util;

import com.testingSystem.entity.Answer;
import com.testingSystem.entity.Question;
import com.testingSystem.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionsUtil {

    @Autowired private QuestionService questionService;

    public void createQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("question1", createAnswers()));
        questions.add(new Question("question2", createAnswers()));
        questions.add(new Question("question3", createAnswers()));
        questions.add(new Question("question4", createAnswers()));
        questions.add(new Question("question5", createAnswers()));
        questions.add(new Question("question6", createAnswers()));
        questions.add(new Question("question7", createAnswers()));
        questions.forEach(questionService::save);
    }

    private List<Answer> createAnswers() {
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("sdfsdf", false));
        answers.add(new Answer("sdfsdfsdf", false));
        answers.add(new Answer("sdsdfsdfsdfsdffsdf", true));
        answers.add(new Answer("sdfsdfsdfsdf", false));
        return answers;
    }
}
