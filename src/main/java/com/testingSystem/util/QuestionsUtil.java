package com.testingSystem.util;

import com.testingSystem.entity.Answer;
import com.testingSystem.entity.Question;
import com.testingSystem.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuestionsUtil {

    @Autowired private QuestionService questionService;

    public void createQuestions() {
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            questions.add(new Question("question" + i, createAnswers()));
        }
        questions.forEach(questionService::save);
    }

    private List<Answer> createAnswers() {
        List<Answer> answers = new ArrayList<>();
        Random random = new Random();
        int answCount = random.nextInt(4) + 1;
        for (int i = 0; i < answCount; i++) {
            answers.add(new Answer(String.valueOf(random.nextDouble()), false));
        }
        answers.add(new Answer(String.valueOf(random.nextDouble()), true));
        return answers;
    }
}
