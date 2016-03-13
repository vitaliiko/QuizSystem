package com.testingSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Question implements Serializable {

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @Column
    private String questionText;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "question_id")
    private List<Answer> answers = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_id")
    private Answer rightAnswer;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "testToQuestionRelation",
            joinColumns = {
                    @JoinColumn(name = "question_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "test_id")
            }
    )
    private List<Test> tests = new ArrayList<>();

    public Question() {
    }

    public Question(String questionText, List<Answer> answers, Answer rightAnswer) {
        this.questionText = questionText;
        this.answers = answers;
        this.rightAnswer = rightAnswer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String text) {
        this.questionText = text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answer getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(Answer rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (id != null ? !id.equals(question.id) : question.id != null) return false;
        if (questionText != null ? !questionText.equals(question.questionText) : question.questionText != null)
            return false;
        if (answers != null ? !answers.equals(question.answers) : question.answers != null) return false;
        if (rightAnswer != null ? !rightAnswer.equals(question.rightAnswer) : question.rightAnswer != null)
            return false;
        return !(tests != null ? !tests.equals(question.tests) : question.tests != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (questionText != null ? questionText.hashCode() : 0);
        result = 31 * result + (answers != null ? answers.hashCode() : 0);
        result = 31 * result + (rightAnswer != null ? rightAnswer.hashCode() : 0);
        result = 31 * result + (tests != null ? tests.hashCode() : 0);
        return result;
    }
}
