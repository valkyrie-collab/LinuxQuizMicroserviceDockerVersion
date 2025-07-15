package com.valkyrie.question_service.controller;

import com.valkyrie.question_service.model.Question;
import com.valkyrie.question_service.model.QuestionWrapper;
import com.valkyrie.question_service.model.Response;
import com.valkyrie.question_service.model.Store;
import com.valkyrie.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    private QuestionService service;
    @Autowired
    private void setService(QuestionService service) {this.service = service;}

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Question question) {
        Store<String> store = service.save(question);
        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody Question question) {return save(question);}

    @GetMapping("/find-all-question")
    public ResponseEntity<List<Question>> getAllQuestion(@RequestParam String category) {
        Store<List<Question>> questions = service.findAllQuestionByCategory(category);
        return ResponseEntity.status(questions.getStatus()).body(questions.getInstance());
    }

    @GetMapping("/find-question")
    public ResponseEntity<Question> getQuestion(@RequestParam int id) {
        Store<Question> store = service.findQuestionById(id);
        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @DeleteMapping("/delete-All-questions")
    public ResponseEntity<String> deleteQuestions(@RequestParam String category) {
        Store<String> store = service.deleteAllQuestionByCategory(category);
        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @DeleteMapping("/delete-question")
    public ResponseEntity<String> deleteQuestion(@RequestParam int id) {
        Store<String> store = service.deleteQuestionById(id);
        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-quiz-question")
    public ResponseEntity<List<Integer>> getQuestionIds(@RequestParam String category,
                                                        @RequestParam int numberOfQuestions) {
        Store<List<Integer>> store = service.getQuestionIds(category, numberOfQuestions);
        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @PostMapping("/get-quiz-question")
    public ResponseEntity<List<QuestionWrapper>> getQuestion(@RequestBody List<Integer> questionIds) {
        System.out.println(questionIds);
        Store<List<QuestionWrapper>> store = service.getQuestions(questionIds);
        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @PostMapping("/calculate-score")
    public ResponseEntity<String> getScore(@RequestBody List<Response> responses) {
        Store<String> store = service.getScore(responses);
        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }
}
