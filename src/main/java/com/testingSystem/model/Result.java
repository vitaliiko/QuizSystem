package com.testingSystem.model;

public class Result {

    private int spentTime;
    private int questionsCount;
    private int rightAnswers;
    private int attempts;
    private String message;

    public Result() {
    }

    public Result(int spentTime, int questionsCount, int rightAnswers, int attempts, String message) {
        this.spentTime = spentTime;
        this.questionsCount = questionsCount;
        this.rightAnswers = rightAnswers;
        this.attempts = attempts;
        this.message = message;
    }

    public int getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(int spentTime) {
        this.spentTime = spentTime;
    }

    public int getQuestionsCount() {
        return questionsCount;
    }

    public void setQuestionsCount(int questionsCount) {
        this.questionsCount = questionsCount;
    }

    public int getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(int rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
