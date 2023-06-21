package vn.hcmuaf.edu.fit.quizbackend.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import vn.hcmuaf.edu.fit.quizbackend.model.exam.Quiz;

@DataJpaTest
class QuizRepositoryTest {

	@Autowired
	private QuizRepository quizRepository;

	@Test
	void testFindByCategory() {
		Quiz quiz = initQuiz();
		List<Quiz> expected = new ArrayList<>();
		quizRepository.save(quiz);
		quizRepository.findByCategory(null).forEach(q -> {
			expected.add(q);
		});
		assertThat(expected.size()).isEqualTo(1);
	}

	@Test
	void testFindByActive() {
		Quiz quiz = initQuiz();
		List<Quiz> expected = new ArrayList<>();
		quizRepository.save(quiz);
		quizRepository.findByActive(true).forEach(q -> {
			expected.add(q);
		});
		assertThat(expected.size()).isEqualTo(1);
	}

	@Test
	void testFindByCategoryAndActive() {
		Quiz quiz = initQuiz();
		List<Quiz> expected = new ArrayList<>();
		quizRepository.save(quiz);
		quizRepository.findByCategoryAndActive(null, true).forEach(q -> {
			expected.add(q);
		});
		assertThat(expected.size()).isEqualTo(1);
	}

	private Quiz initQuiz() {
		Quiz quiz = new Quiz();
		quiz.setTitle("Mahesh");
		quiz.setQuestions(null);
		quiz.setActive(true);
		quiz.setCategory(null);
		quiz.setDescription("description");
		quiz.setMaxMarks("200");
		quiz.setNumberOfQuestions("10");
		return quiz;
	}

}
