package com.my.ftt.service;
import com.my.ftt.domain.Answer;
import com.my.ftt.domain.Question;
import com.my.ftt.domain.QuizDto;
import com.my.ftt.domain.RequestQuiz;
import com.my.ftt.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;

    public void saveQuizList(List<RequestQuiz> requestQuizList) {

        int size = 0;
        for (RequestQuiz requestQuiz : requestQuizList) {
            size ++;
            Question question = new Question();

            question.setQuestion(requestQuiz.getQuestion());
            question.setType(requestQuiz.getType());
            question.setDifficulty(requestQuiz.getDifficulty());

            List<Answer> answers = new ArrayList<>();

            Answer answer = new Answer();
            answer.setAnswer(requestQuiz.getCorrect_answer());
            answer.setCorrect(true);
            answer.setQuestion(question);
            answers.add(answer);

            for (String incorrectAnswer : requestQuiz.getIncorrect_answers()) {
                Answer answer1 = new Answer();
                answer1.setAnswer(incorrectAnswer);
                answer1.setCorrect(false);
                answer1.setQuestion(question);
                answers.add(answer1);
            }
            question.setAnswers(answers);
            quizRepository.saveQuiz(question);


        }
        System.out.println(size);
    }

    public List<QuizDto> getQuizByDifficulty(String difficulty) {
        List<Question> questions = quizRepository.findByDifficulty(difficulty);

        for (Question q: questions) {
            String originalQuestion = q.getQuestion();
            String unescapedQuestion = HtmlUtils.htmlUnescape(originalQuestion);
            q.setQuestion(unescapedQuestion);

            Collections.shuffle(q.getAnswers());

        }

        List<QuizDto> quizDtos = questions.stream().map(question -> new QuizDto(question))
                .collect(Collectors.toList());
        return quizDtos;
    }
}
