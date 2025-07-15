package com.valkyrie.quiz_service.repository;

import com.valkyrie.quiz_service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    Quiz findByTitle(String title);

    List<Quiz> findByCategory(String category);
}
