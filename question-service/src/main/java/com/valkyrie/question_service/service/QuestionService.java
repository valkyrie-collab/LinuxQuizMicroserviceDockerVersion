package com.valkyrie.question_service.service;

import com.valkyrie.question_service.model.Question;
import com.valkyrie.question_service.model.QuestionWrapper;
import com.valkyrie.question_service.model.Response;
import com.valkyrie.question_service.model.Store;
import com.valkyrie.question_service.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionService {
    private static final Question defaultQuestion = new Question().setId(-1).setDifficultyLevel("null")
            .setQuestionTitle("null").setCategory("null").setOptionOne("null")
            .setOptionTwo("null").setOptionThree("null").setOptionFour("null");
    private QuestionRepository repo;
    @Autowired
    private void setRepo(QuestionRepository repo) {this.repo = repo;}

    public Store<String> save(Question question) {
        Question present = repo.findByQuestionTitle(question.getQuestionTitle());

        if (present == null) {
            repo.save(question);
        } else if (!present.toString().equals(question.toString())) {
            question = question.setId(present.getId());
            repo.save(question);
            return repo.findById(question.getId()).orElse(null) == null?
                    Store.initialize(HttpStatus.BAD_REQUEST, "Question is not updated successfully") :
                    Store.initialize(HttpStatus.OK,
                            "Question with ID = " + question.getId() + "updated successfully");
        }

        return repo.findById(question.getId()).orElse(null) == null?
                Store.initialize(HttpStatus.NOT_ACCEPTABLE, "Question not saved") :
                Store.initialize(HttpStatus.ACCEPTED,
                        "Question with ID = " + question.getId() + " saved Successfully");
    }

    public Store<Question> findQuestionById(int id) {
        Question question = repo.findById(id).orElse(null);
        return question == null? Store.initialize(HttpStatus.BAD_REQUEST, defaultQuestion) :
                Store.initialize(HttpStatus.OK, question);
    }


    public Store<List<Question>> findAllQuestionByCategory(String category) {
        List<Question> questions = repo.findAllByCategory(category);
        return questions.isEmpty()? Store.initialize(HttpStatus.BAD_REQUEST, List.of(defaultQuestion)) :
                Store.initialize(HttpStatus.OK, questions);
    }

    public Store<String> deleteQuestionById(int id) {

        if (repo.findById(id).orElse(null) == null) {
            return Store.initialize(HttpStatus.OK, "Question with ID = " + id + " was already deleted");
        }

        repo.deleteById(id);
        return repo.findById(id).orElse(null) == null?
                Store.initialize(HttpStatus.OK, "Question with ID = " + id + " Successfully deleted") :
                Store.initialize(HttpStatus.BAD_REQUEST, "Question is not deleted");
    }

    @Transactional
    public Store<String> deleteAllQuestionByCategory(String category) {
        List<Question> store = repo.findAllByCategory(category);

        if (store.isEmpty()) {
            return Store.initialize(HttpStatus.OK,
                    "There is no question with category = " + category + " to delete");
        }

        repo.deleteAllByCategory(category);
        return repo.findAllByCategory(category).isEmpty()?
                Store.initialize(HttpStatus.OK,
                        "All Question with category = " + category + " successfully deleted") :
                Store.initialize(HttpStatus.BAD_REQUEST, "Questions are not deleted successfully");
    }

    public Store<List<Integer>> getQuestionIds(String category, int numberOfQuestion) {
        List<Question> questions = repo.findAllByCategory(category);
        Collections.shuffle(questions);

        if (questions.isEmpty()) {return Store.initialize(HttpStatus.BAD_REQUEST, List.of(-1));}

        return Store.initialize(HttpStatus.OK,
                questions.stream().map(Question::getId).limit(numberOfQuestion).toList());
    }

    public Store<List<QuestionWrapper>> getQuestions(List<Integer> questionIds) {
        List<QuestionWrapper> questions = new ArrayList<>();

        for (int id : questionIds) {
            repo.findById(id).ifPresent(question -> questions.add(
                    new QuestionWrapper().setId(question.getId()).setQuestionTitle(question.getQuestionTitle())
                            .setOptionOne(question.getOptionOne()).setOptionTwo(question.getOptionTwo())
                            .setOptionFour(question.getOptionFour()).setOptionThree(question.getOptionThree())
            ));
        }

        return questions.isEmpty()? Store.initialize(HttpStatus.BAD_REQUEST, null) :
                Store.initialize(HttpStatus.OK, questions);
    }

    public Store<String> getScore(List<Response> responses) {
        double score = 0.0;

        for (Response response : responses) {
            Question question = repo.findById(response.getQuestionId()).orElse(null);

            if (question != null && response.getResponse().equals(question.getRightAnswer())) {
                score++;
            } else if (question != null) {
                score -= 0.6;
            } else {
                return Store.initialize(HttpStatus.BAD_REQUEST,
                        "No such question with ID = " + response.getQuestionId() + " is there.");
            }
        }

        return Store.initialize(HttpStatus.OK, "Your Score is " + score);
    }

}
