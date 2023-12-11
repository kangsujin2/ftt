package com.my.ftt.controller;

import com.my.ftt.domain.QuizDto;
import com.my.ftt.domain.RequestQuiz;
import com.my.ftt.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/initQuiz")
    public void initQuizDB(@RequestBody List<RequestQuiz> requestQuizList) {
        quizService.saveQuizList(requestQuizList);
    }

    @GetMapping("/quiz/{difficulty}")
    public List<QuizDto> getQuiz(@PathVariable String difficulty) {
        return quizService.getQuizByDifficulty(difficulty);
    }

}
