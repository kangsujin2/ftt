package com.my.ftt;

import com.my.ftt.demo.QuizRepository;
import com.my.ftt.demo.domain.Answer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DemoTest {

    @Autowired
    QuizRepository quizRepository;

    @Test
    public void printAllAnswer(){
        List<Object[]> resultList = quizRepository.findAll();

        for (Object[] o : resultList) {
            System.out.println(o);
        }
    }

    @Test
    public void printOneQuestion(){
        String question;
        question = quizRepository.getOneQuiz();
        System.out.println(question);
    }
}
