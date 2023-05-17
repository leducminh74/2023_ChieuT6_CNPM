package vn.hcmuaf.edu.fit.quizbackend.service;

import java.util.Set;

import vn.hcmuaf.edu.fit.quizbackend.model.exam.Question;
import vn.hcmuaf.edu.fit.quizbackend.model.exam.Quiz;

public interface QuestionService {

	public Question addQuestion(Question question);

	public Question updateQuestion(Question question);

	public Set<Question> getQuestions();

	public Question getQuestion(Long questionId);

	public Set<Question> getQuestionsOfQuiz(Quiz quiz);
	
	public void deleteQuestion(Long quesId);
	
	public Question get(Long id);

}
