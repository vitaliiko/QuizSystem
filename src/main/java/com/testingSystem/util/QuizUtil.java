package com.testingSystem.util;

import com.testingSystem.entity.Answer;
import com.testingSystem.entity.Question;
import com.testingSystem.exception.WrongFormatException;
import com.testingSystem.model.QuestionModel;
import com.testingSystem.service.QuestionService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class QuizUtil {

    public static final int QUESTIONS_COUNT = 5;
    public static final int TIME_LIMIT = 30;
    private Random random = new Random();

    @Autowired private QuestionService questionService;

    public void createQuestions() throws HibernateException {
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            List<Answer> answers = createAnswers();
            questions.add(new Question("question" + i, answers, answers.get(random.nextInt(answers.size()))));
        }
        questions.forEach(questionService::save);
    }

    public void createQuestion(QuestionModel questionModel) throws WrongFormatException {
        String questionText = questionModel.getQuestion();
        if (questionText.isEmpty()) {
            throw new WrongFormatException("Question text doesn't exist");
        }
        List<Answer> answers = new ArrayList<>();
        for (String a : questionModel.getAnswers()) {
            Answer answer = new Answer(a);
            if (answers.contains(answer)) {
                throw new WrongFormatException("Duplicate answers detected");
            }
            answers.add(answer);
        }
        Arrays.stream(questionModel.getAnswers())
                .filter(a -> !a.isEmpty())
                .forEach(a -> answers.add(new Answer(a)));
        if (answers.size() < 2) {
            throw new WrongFormatException("Answers count less then 2");
        }
        Answer rightAnswer = answers.get(questionModel.getRightAnswer());
        if (rightAnswer == null) {
            throw new WrongFormatException("Right answer doesn't exist");
        }
        Question question = new Question(questionText, answers, rightAnswer);
        questionService.save(question);
    }

    private List<Answer> createAnswers() {
        List<Answer> answers = new ArrayList<>();
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
}
