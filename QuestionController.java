package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Repo.QuestionRepository;
import com.example.demo.model.Question;


@RestController
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @PostMapping
    public Question addQuestion(@RequestBody Question question) {
        return questionRepository.save(question);
    }

    @GetMapping("/{id}")
    public Optional<Question> getQuestion(@PathVariable Long id) {
        return questionRepository.findById(id);
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
}
