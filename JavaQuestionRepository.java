package com.example.demo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.JavaQuestion;

public interface JavaQuestionRepository  extends JpaRepository<JavaQuestion, Long>{

	List<JavaQuestion> findAll();

	@SuppressWarnings("unchecked")
	JavaQuestion save(JavaQuestion question);

}
