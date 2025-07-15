package com.valkyrie.question_service.repository;

import com.valkyrie.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findByQuestionTitle(String questionTitle);

    List<Question> findAllByCategory(String category);

    void deleteAllByCategory(String category);
}
