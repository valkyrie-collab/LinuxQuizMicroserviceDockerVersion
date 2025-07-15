package com.valkyrie.quiz_service.service;

import com.valkyrie.quiz_service.feign.QuestionFeignController;
import com.valkyrie.quiz_service.model.*;
import com.valkyrie.quiz_service.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuizService {
    private QuizRepository repo;
    @Autowired
    private void setRepo(QuizRepository repo) {this.repo = repo;}

    private QuestionFeignController feign;
    @Autowired
    private void setFeign(QuestionFeignController feign) {this.feign = feign;}

    public Store<String> save(String title, String category, int numberOfQuestions) {
        List<Integer> questionIds = feign.getQuestionIds(category, numberOfQuestions).getBody();

        if (questionIds == null || questionIds.isEmpty()) {
            return Store.initialize(HttpStatus.NOT_ACCEPTABLE, "Question service issue");
        }

        Quiz quiz = new Quiz().setCategory(category).setTitle(title).setQuestionIds(questionIds),
            present = repo.findByTitle(title);

        if (present == null) {
            repo.save(quiz);
        } else if (!present.toString().equals(quiz.toString())) {
            quiz = quiz.setId(present.getId());
            repo.save(quiz);
            return repo.findById(quiz.getId()).orElse(null) == null?
                    Store.initialize(HttpStatus.NOT_ACCEPTABLE, "quiz is not updated") :
                    Store.initialize(HttpStatus.ACCEPTED,
                            "quiz with title = " + title + " updated successfully");
        }

        return repo.findById(quiz.getId()).orElse(null) == null?
                Store.initialize(HttpStatus.NOT_ACCEPTABLE, "Quiz not saved") :
                Store.initialize(HttpStatus.ACCEPTED, "Quiz saved with ID = " + quiz.getId());
    }

    public Store<List<QuizWrapper>> getQuiz(String category) {
        List<Quiz> quizzes = repo.findByCategory(category);

        if (quizzes == null || quizzes.isEmpty()) {
            return Store.initialize(HttpStatus.BAD_REQUEST, new ArrayList<>());
        }

        List<QuizWrapper> displayQuizzes = new ArrayList<>();

        for (Quiz quiz : quizzes) {
            List<QuestionWrapper> questions = feign.getQuestion(quiz.getQuestionIds()).getBody();

            if (questions == null || questions.isEmpty()) {
                return Store.initialize(HttpStatus.BAD_REQUEST, new ArrayList<>());
            }

            Collections.shuffle(questions);
            displayQuizzes.add(new QuizWrapper().setId(quiz.getId()).setTitle(quiz.getTitle())
                    .setCategory(quiz.getCategory()).setQuestions(questions)
            );
        }

        Collections.shuffle(displayQuizzes);
        return Store.initialize(HttpStatus.OK, displayQuizzes);
    }

    public ResponseEntity<String> getScore(List<Response> responses) {
        return feign.getScore(responses);
    }
}

