package vn.hcmuaf.edu.fit.quizbackend.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import vn.hcmuaf.edu.fit.quizbackend.model.exam.Question;

@DataJpaTest
class QuestionRepositoryTest {

	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void testFindByQuiz() {
		Question question = initQuestion();
		List<Question> expected = new ArrayList<>();
		questionRepository.save(question);
		questionRepository.findByQuiz(null).forEach(q -> {
			expected.add(q);
		});
		assertThat(expected.size()).isEqualTo(1);
		
	
	}

	private Question initQuestion() {
		Question question = new Question("123","123","123", "123", "123", "123", "123", "123", null);
		return question;
	}

}
