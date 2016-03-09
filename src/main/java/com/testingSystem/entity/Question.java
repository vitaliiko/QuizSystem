package com.testingSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

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
    private Long id;

    @Column
    private String questionText;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "question_id")
    private List<Answer> answers = new ArrayList<>();

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

    public Question(String questionText, List<Answer> answers) {
        this.questionText = questionText;
        this.answers = answers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
}
