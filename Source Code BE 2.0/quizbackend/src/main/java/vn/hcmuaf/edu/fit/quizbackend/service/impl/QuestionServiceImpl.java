package vn.hcmuaf.edu.fit.quizbackend.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.hcmuaf.edu.fit.quizbackend.model.exam.Question;
import vn.hcmuaf.edu.fit.quizbackend.model.exam.Quiz;
import vn.hcmuaf.edu.fit.quizbackend.repository.QuestionRepository;
import vn.hcmuaf.edu.fit.quizbackend.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	private QuestionRepository questionRepository;
	
	@Override
	public Question addQuestion(Question question) {
		
		return this.questionRepository.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
	
		return this.questionRepository.save(question);
	}

	@Override
	public Set<Question> getQuestions() {
		
		return new HashSet<>(this.questionRepository.findAll());
	}

	@Override
	public Optional<Question> getQuestion(Long questionId) {
		return this.questionRepository.findById(questionId);
	}

	@Override
	public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
		return this.questionRepository.findByQuiz(quiz);
	}

	@Override
	public void deleteQuestion(Long quesId) {
		this.questionRepository.deleteById(quesId);
		
	}

	@Override
	public Question get(Long id) {
		return this.questionRepository.findById(id).get();
	}

}
