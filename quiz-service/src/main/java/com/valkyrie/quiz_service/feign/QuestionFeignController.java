package com.valkyrie.quiz_service.feign;

import com.valkyrie.quiz_service.model.QuestionWrapper;
import com.valkyrie.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuestionFeignController {

    @GetMapping("/question/find-quiz-question")
    ResponseEntity<List<Integer>> getQuestionIds(@RequestParam String category,
                                                        @RequestParam int numberOfQuestions);

    @PostMapping("/question/get-quiz-question")
    ResponseEntity<List<QuestionWrapper>> getQuestion(@RequestBody List<Integer> questionIds);

    @PostMapping("/question/calculate-score")
    ResponseEntity<String> getScore(@RequestBody List<Response> responses);
}
