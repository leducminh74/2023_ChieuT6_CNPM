package vn.hcmuaf.edu.fit.quizbackend.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hcmuaf.edu.fit.quizbackend.model.exam.Question;
import vn.hcmuaf.edu.fit.quizbackend.model.exam.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	Set<Question> findByQuiz(Quiz quiz);

}
