package com.my.ftt.repository;

import com.my.ftt.domain.Question;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class QuizRepository {

    @PersistenceContext
    private EntityManager em;

    public void saveQuiz(Question question) {
        em.persist(question);
    }

    public List<Question> findByDifficulty(String difficulty) {
        String query = "select distinct q from Question q join fetch q.answers where q.difficulty = :difficulty";
        List<Question> questions = em.createQuery(query, Question.class).setParameter("difficulty", difficulty).setMaxResults(3).getResultList();
        return questions;
    }
}
