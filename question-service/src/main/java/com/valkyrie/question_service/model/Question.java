package com.valkyrie.question_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String difficultyLevel;
    private String questionTitle;
    private String category;
    private String optionOne;
    private String optionTwo;
    private String optionThree;
    private String optionFour;
    private String rightAnswer;

    public int getId() {return id;}

    public String getDifficultyLevel() {return difficultyLevel;}

    public String getQuestionTitle() {return questionTitle;}

    public String getCategory() {return category;}

    public String getOptionOne() {return optionOne;}

    public String getOptionTwo() {return optionTwo;}

    public String getOptionThree() {return optionThree;}

    public String getOptionFour() {return optionFour;}

    public String getRightAnswer() {return rightAnswer;}

    public Question setId(int id) {
        this.id = id;
        return this;
    }

    public Question setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
        return this;
    }

    public Question setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
        return this;
    }

    public Question setCategory(String category) {
        this.category = category;
        return this;
    }

    public Question setOptionOne(String optionOne) {
        this.optionOne = optionOne;
        return this;
    }

    public Question setOptionTwo(String optionTwo) {
        this.optionTwo = optionTwo;
        return this;
    }

    public Question setOptionThree(String optionThree) {
        this.optionThree = optionThree;
        return this;
    }

    public Question setOptionFour(String optionFour) {
        this.optionFour = optionFour;
        return this;
    }

    public Question setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
        return this;
    }

    @Override
    public String toString() {
        return "Question{" +
                "difficultyLevel='" + difficultyLevel + '\'' +
                ", questionTitle='" + questionTitle + '\'' +
                ", category='" + category + '\'' +
                ", optionOne='" + optionOne + '\'' +
                ", optionTwo='" + optionTwo + '\'' +
                ", optionThree='" + optionThree + '\'' +
                ", optionFour='" + optionFour + '\'' +
                '}';
    }
}
