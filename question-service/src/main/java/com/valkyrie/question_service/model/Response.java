package com.valkyrie.question_service.model;

public class Response {
    private int questionId;
    private String response;

    public int getQuestionId() {return questionId;}

    public String getResponse() {return response;}

    public Response setQuestionId(int questionId) {
        this.questionId = questionId;
        return this;
    }

    public Response setResponse(String response) {
        this.response = response;
        return this;
    }

    @Override
    public String toString() {
        return "Response{" +
                "questionId=" + questionId +
                ", response='" + response + '\'' +
                '}';
    }
}
