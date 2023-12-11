package com.my.ftt.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class QuizDto {
    private int questionId;
    private String question;
    private String difficulty;
    private String type;
    private List<Answer> answers = new ArrayList<>();

    public QuizDto(Question question) {
        this.questionId = question.getId();
        this.question = question.getQuestion();
        this.difficulty = question.getDifficulty();
        this.type = question.getType();
        question.getAnswers().stream().forEach(answer -> answer.getAnswer());
        this.answers = question.getAnswers();

    }
}
