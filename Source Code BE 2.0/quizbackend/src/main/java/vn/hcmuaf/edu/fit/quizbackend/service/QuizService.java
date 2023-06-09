package vn.hcmuaf.edu.fit.quizbackend.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import vn.hcmuaf.edu.fit.quizbackend.model.exam.Category;
import vn.hcmuaf.edu.fit.quizbackend.model.exam.Quiz;

public interface QuizService {

	public Quiz addQuiz(Quiz quiz);

	public Quiz updateQuiz(Quiz quiz);

	public Set<Quiz> getQuizzes();

	public Optional<Quiz> getQuiz(Long quizId);

	public void deleteQuiz(Long quizId);

	public List<Quiz> getQuizzesOfCategory(Category category);

	public List<Quiz> getActiveQuizzes();

	public List<Quiz> getActiveQuizzesOfCategory(Category c);

}
