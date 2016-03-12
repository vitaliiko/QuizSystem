package com.testingSystem.util;

import com.testingSystem.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TestUtil {

    @Autowired private QuestionService questionService;

    public int countRightAnswers(Map<Integer, String> userAnswers) {
        final int[] answersCount = {0};
        userAnswers.forEach((questionId, answer) -> {
            if (questionService.getById(questionId).getRightAnswer().getText().equals(answer)) {
                answersCount[0]++;
            }
        });
        return answersCount[0];
    }

    public int countSpentTime(long startTime) {
        long currentTime = System.currentTimeMillis();
        return (int) ((currentTime - startTime) / 60 * 1000);
    }

    public float countResult(int questionsCount, int rightAnswers) {
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
}
