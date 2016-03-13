package com.testingSystem.model;

public class Result {

    private String spentTime;
    private int questionsCount;
    private int rightAnswers;
    private float result;
    private int attempts;
    private String messageText;

    public Result() {
    }

    public Result(String spentTime, int questionsCount, int rightAnswers, float result, int attempts, String messageText) {
        this.spentTime = spentTime;
        this.questionsCount = questionsCount;
        this.rightAnswers = rightAnswers;
        this.result = result;
        this.attempts = attempts;
        this.messageText = messageText;
    }

    public String getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(String spentTime) {
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

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
