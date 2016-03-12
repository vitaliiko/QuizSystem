package com.testingSystem.util;

import com.testingSystem.entity.Answer;
import com.testingSystem.entity.Question;
import com.testingSystem.service.QuestionService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionUtil {

    public static final int QUESTIONS_COUNT = 10;

    @Autowired private QuestionService questionService;

    public void createQuestions() throws HibernateException {
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            questions.add(new Question("question" + i, createAnswers()));
        }
        questions.forEach(questionService::save);
    }

    private List<Answer> createAnswers() {
        List<Answer> answers = new ArrayList<>();
        Random random = new Random();
        int answCount = random.nextInt(4) + 1;
        for (int i = 0; i < answCount; i++) {
            answers.add(new Answer(String.valueOf(random.nextDouble())));
        }
        answers.add(new Answer(String.valueOf(random.nextDouble())));
        return answers;
    }

    public Set<Integer> getRandomQuestions(int count) throws HibernateException {
        Set<Integer> questionsIdSet = new HashSet<>();
        List<Question> questionList = questionService.getAll("id");
        while (questionsIdSet.size() < count) {
            int randomIndex = (int) (Math.random() * questionList.size());
            questionsIdSet.add(questionList.get(randomIndex).getId());
        }
        return questionsIdSet;
    }

    public Question getQuestion(Set<Integer> idSet) throws HibernateException {
        if (idSet.size() > 0) {
            Integer generatedQuestionId = idSet.stream().findFirst().get();
            idSet.remove(generatedQuestionId);
            return questionService.getById(generatedQuestionId);
        }
        return null;
    }

    public int countRightAnswers(Map<Integer, String> userAnswers) {
        final int[] answersCount = {0};
        userAnswers.forEach((questionId, answer) -> {
            if (questionService.getById(questionId).getRightAnswer().getText().equals(answer)) {
                answersCount[0]++;
            }
        });
    }
}
