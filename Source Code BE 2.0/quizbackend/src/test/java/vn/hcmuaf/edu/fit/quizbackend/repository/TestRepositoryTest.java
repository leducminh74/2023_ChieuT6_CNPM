package vn.hcmuaf.edu.fit.quizbackend.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class TestRepositoryTest {

	@Autowired
	private TestRepository testRepository;

	@Test
	void testFindByUserAndIsEnd() {
		vn.hcmuaf.edu.fit.quizbackend.model.Test test = initTest();
		List<vn.hcmuaf.edu.fit.quizbackend.model.Test> expected = new ArrayList<>();
		testRepository.save(test);

		testRepository.findByUserAndIsEnd(null, false).forEach(e -> {
			expected.add(e);
		});

		assertThat(expected.size()).isEqualTo(1);

	}

	@Test
	void testFindByQuizAndIsEndOrderByMarksDesc() {
		vn.hcmuaf.edu.fit.quizbackend.model.Test test = initTest();
		List<vn.hcmuaf.edu.fit.quizbackend.model.Test> expected = new ArrayList<>();
		testRepository.save(test);
		testRepository.findByQuizAndIsEndOrderByMarksDesc(null, false).forEach(e -> {
			expected.add(e);
		});
		assertThat(expected.size()).isEqualTo(1);
	}

	@Test
	void testFindByQuizAndIsEnd() {
		vn.hcmuaf.edu.fit.quizbackend.model.Test test = initTest();
		List<vn.hcmuaf.edu.fit.quizbackend.model.Test> expected = new ArrayList<>();
		testRepository.save(test);
		testRepository.findByQuizAndIsEnd(null, false).forEach(e -> {
			expected.add(e);
		});

		assertThat(expected.size()).isEqualTo(1);
	}

	private vn.hcmuaf.edu.fit.quizbackend.model.Test initTest() {
		vn.hcmuaf.edu.fit.quizbackend.model.Test test = new vn.hcmuaf.edu.fit.quizbackend.model.Test(null, null, 0, 0,
				0, null, null, null, 0);
		return test;
	}

}
