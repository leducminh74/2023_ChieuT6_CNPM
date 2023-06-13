package vn.hcmuaf.edu.fit.quizbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hcmuaf.edu.fit.quizbackend.model.Test;
import vn.hcmuaf.edu.fit.quizbackend.model.User;
import vn.hcmuaf.edu.fit.quizbackend.model.exam.Quiz;

public interface TestRepository extends JpaRepository<Test, Long> {

	List<Test> findByUserAndIsEnd(User user, boolean isEnd);

	List<Test> findByQuizAndIsEndOrderByMarksDesc(Quiz quiz, boolean isEnd);

	List<Test> findByQuizAndIsEnd(Quiz quiz,boolean isEnd);
}
