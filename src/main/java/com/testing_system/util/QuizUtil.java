package com.testing_system.util;

import com.testing_system.entity.Answer;
import com.testing_system.entity.Question;
import com.testing_system.request_object.Result;
import com.testing_system.service.QuestionService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class QuizUtil {

    public static final int QUESTIONS_COUNT = 5;
    public static final int TIME_LIMIT = 30;

    @Autowired private QuestionService questionService;
    @Autowired private ContentLoader contentLoader;

    public Set<Integer> getRandomQuestions(int count) throws HibernateException {
        Set<Integer> questionsIdSet = new HashSet<>();
        List<Question> questionList = questionService.getAll("id");
        if (questionList.size() < count) {
            throw new HibernateException("");
        }
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

    public int countRightAnswers(Map<Integer, Integer> userAnswers) throws HibernateException {
        final int[] answersCount = {0};
        userAnswers.forEach((questionId, answer) -> {
            Question question = questionService.getById(questionId);
            if (question.getRightAnswer().equals(answer)) {
                answersCount[0]++;
            }
        });
        return answersCount[0];
    }

    public String countSpentTime(long startTime) {
        long currentTime = System.currentTimeMillis();
        long spentTime = Math.abs(currentTime - startTime);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(spentTime);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(spentTime) - minutes * 60;
        return minutes + " minutes, " + seconds + " seconds";
    }

    public float countResult(float questionsCount, float rightAnswers) {
        return rightAnswers / questionsCount * 10;
    }

    public String generateMessage(float result) {
        if (result == 0) {
            return "Loser!";
        }
        if (result > 0 && result < 5) {
            return "BAD!";
        }
        if (result > 4 && result < 8) {
            return "Not bad!";
        }
        if (result > 7 && result < 10) {
            return "Nice!";
        }
        return "Awesome!";
    }

    public Result prepareResult(Map<Integer, Integer> answersMap, long time, int attempts) {
        int rightAnswersCount = countRightAnswers(answersMap);
        String spentTime = countSpentTime(time);
        float result = countResult(QuizUtil.QUESTIONS_COUNT, rightAnswersCount);
        String message = generateMessage(result);
        return new Result(spentTime, QuizUtil.QUESTIONS_COUNT, rightAnswersCount, result, attempts, message);
    }

    public void fillDB() {
        if (questionService.getAll("id").size() == 0) {
            contentLoader.loadContent();
        }
    }
}
