package com.valkyrie.quiz_service.controller;

import com.valkyrie.quiz_service.model.QuizWrapper;
import com.valkyrie.quiz_service.model.Response;
import com.valkyrie.quiz_service.model.Store;
import com.valkyrie.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    private QuizService service;
    @Autowired
    private void setService(QuizService service) {this.service = service;}

    @PostMapping("/create-quiz")
    public ResponseEntity<String> save(@RequestParam String title,
                                       @RequestParam String category,
                                       @RequestParam int numberOfQuestions) {
        Store<String> store = service.save(title, category, numberOfQuestions);
        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/display-quiz")
    public ResponseEntity<List<QuizWrapper>> quiz(@RequestParam String category) {
        Store<List<QuizWrapper>> store = service.getQuiz(category);
        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/scored-points")
    public ResponseEntity<String> score(@RequestBody List<Response> responses) {
        return service.getScore(responses);
    }
}
