package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Repo.JavaQuestionRepository;
import com.example.demo.model.JavaQuestion;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/questions")
public class JavaQuestionController {
    @Autowired
    private JavaQuestionRepository javaQuestionRepository;

    @GetMapping
    public Iterable<JavaQuestion> getAllQuestions() {
        return javaQuestionRepository.findAll();
    }

    @PostMapping
    public JavaQuestion addQuestion(@RequestBody JavaQuestion question) {
        return javaQuestionRepository.save(question);
    }
    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        javaQuestionRepository.deleteById(id);
    }
    @PutMapping("/{id}")
    public JavaQuestion editQuestion(@PathVariable Long id, @RequestBody JavaQuestion updatedQuestion) {
        // Find the question by ID
        JavaQuestion existingQuestion = javaQuestionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Question not found with id: " + id));

        // Update the question fields with the new data
        existingQuestion.setQuestion(updatedQuestion.getQuestion());
        existingQuestion.setCorrectAnswer(updatedQuestion.getCorrectAnswer());
        existingQuestion.setOptions(updatedQuestion.getOptions());

        // Save the updated question
        return javaQuestionRepository.save(existingQuestion);
    }

}
