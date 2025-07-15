package com.valkyrie.quiz_service.model;

import java.util.List;

public class QuizWrapper {
    private int id;
    private String title;
    private String category;
    private List<QuestionWrapper> questions;

    public int getId() {return id;}

    public String getTitle() {return title;}

    public String getCategory() {return category;}

    public List<QuestionWrapper> getQuestions() {return questions;}

    public QuizWrapper setId(int id) {
        this.id = id;
        return this;
    }

    public QuizWrapper setTitle(String title) {
        this.title = title;
        return this;
    }

    public QuizWrapper setCategory(String category) {
        this.category = category;
        return this;
    }

    public QuizWrapper setQuestions(List<QuestionWrapper> questions) {
        this.questions = questions;
        return this;
    }

    @Override
    public String toString() {
        return "QuizWrapper{" +
                "title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", questions=" + questions +
                '}';
    }
}

