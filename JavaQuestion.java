package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class JavaQuestion {
      
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String question;
	    private String correctAnswer;
	    private String[] options;
	    
	    public JavaQuestion() {
	        // You can leave it empty or initialize any default values here
	    }
	    
		public JavaQuestion(Long id, String question, String correctAnswer, String[] options) {
			super();
			this.id = id;
			this.question = question;
			this.correctAnswer = correctAnswer;
			this.options = options;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getQuestion() {
			return question;
		}
		public void setQuestion(String question) {
			this.question = question;
		}
		public String getCorrectAnswer() {
			return correctAnswer;
		}
		public void setCorrectAnswer(String correctAnswer) {
			this.correctAnswer = correctAnswer;
		}
		public String[] getOptions() {
			return options;
		}
		public void setOptions(String[] options) {
			this.options = options;
		}

	   
	    
	    
	

}
