package com.valkyrie.quiz_service.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String category;
    @ElementCollection
    private List<Integer> questionIds;

    public int getId() {return id;}

    public String getTitle() {return title;}

    public String getCategory() {return category;}

    public List<Integer> getQuestionIds() {return questionIds;}

    public Quiz setId(int id) {
        this.id = id;
        return this;
    }

    public Quiz setTitle(String title) {
        this.title = title;
        return this;
    }

    public Quiz setCategory(String category) {
        this.category = category;
        return this;
    }

    public Quiz setQuestionIds(List<Integer> questionIds) {
        this.questionIds = questionIds;
        return this;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", questionIds=" + questionIds +
                '}';
    }
}
