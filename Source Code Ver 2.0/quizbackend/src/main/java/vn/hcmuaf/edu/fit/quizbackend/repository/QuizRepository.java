package vn.hcmuaf.edu.fit.quizbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hcmuaf.edu.fit.quizbackend.model.exam.Category;
import vn.hcmuaf.edu.fit.quizbackend.model.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

	List<Quiz> findByCategory(Category category);
	
	List<Quiz> findByActive(Boolean b);
	
	List<Quiz> findByCategoryAndActive(Category c, Boolean b);

}
