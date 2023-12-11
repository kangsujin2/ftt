package com.my.ftt.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class RequestQuiz {

    private String type;
    private String difficulty;
    private String question;
    private String correct_answer;
    private List<String> incorrect_answers = new ArrayList<>();
}
