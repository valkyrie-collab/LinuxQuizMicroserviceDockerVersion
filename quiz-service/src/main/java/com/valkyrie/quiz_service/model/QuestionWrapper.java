package com.valkyrie.quiz_service.model;

public class QuestionWrapper {
    private int id;
    private String questionTitle;
    private String optionOne;
    private String optionTwo;
    private String optionThree;
    private String optionFour;

    public int getId() {return id;}

    public String getQuestionTitle() {return questionTitle;}

    public String getOptionOne() {return optionOne;}

    public String getOptionTwo() {return optionTwo;}

    public String getOptionThree() {return optionThree;}

    public String getOptionFour() {return optionFour;}

    public QuestionWrapper setId(int id) {
        this.id = id;
        return this;
    }

    public QuestionWrapper setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
        return this;
    }

    public QuestionWrapper setOptionOne(String optionOne) {
        this.optionOne = optionOne;
        return this;
    }

    public QuestionWrapper setOptionTwo(String optionTwo) {
        this.optionTwo = optionTwo;
        return this;
    }

    public QuestionWrapper setOptionThree(String optionThree) {
        this.optionThree = optionThree;
        return this;
    }

    public QuestionWrapper setOptionFour(String optionFour) {
        this.optionFour = optionFour;
        return this;
    }

    @Override
    public String toString() {
        return "QuestionWrapper{" +
                "questionTitle='" + questionTitle + '\'' +
                ", optionOne='" + optionOne + '\'' +
                ", optionTwo='" + optionTwo + '\'' +
                ", optionThree='" + optionThree + '\'' +
                ", optionFour='" + optionFour + '\'' +
                '}';
    }
}
