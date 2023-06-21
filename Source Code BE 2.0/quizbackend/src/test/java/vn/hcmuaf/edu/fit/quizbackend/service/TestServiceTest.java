package vn.hcmuaf.edu.fit.quizbackend.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;

import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import vn.hcmuaf.edu.fit.quizbackend.model.User;
import vn.hcmuaf.edu.fit.quizbackend.model.exam.Quiz;
import vn.hcmuaf.edu.fit.quizbackend.repository.TestRepository;
import vn.hcmuaf.edu.fit.quizbackend.service.impl.TestServiceImpl;

@ExtendWith(MockitoExtension.class)
class TestServiceTest {

	@Mock
	private TestRepository testRepository;

	@InjectMocks
	private TestServiceImpl testService;

	@Test
	void testGetTestByUser() {
		testService.getTestByUser(new User());
		verify(testRepository).findByUserAndIsEnd(any(User.class), anyBoolean());
	}

	@Test
	void testStartTest() {
		vn.hcmuaf.edu.fit.quizbackend.model.Test startTest = new vn.hcmuaf.edu.fit.quizbackend.model.Test();
		testService.startTest(startTest);
		ArgumentCaptor<vn.hcmuaf.edu.fit.quizbackend.model.Test> testArgumentCaptor = ArgumentCaptor
				.forClass(vn.hcmuaf.edu.fit.quizbackend.model.Test.class);
		verify(testRepository).save(testArgumentCaptor.capture());
		vn.hcmuaf.edu.fit.quizbackend.model.Test capturedTest = testArgumentCaptor.getValue();
		assertThat(capturedTest).isEqualTo(startTest);
	}

	@Test
	void testEndTest() {
		Test t = (Test) testService.endTest(null);
		assertThat(t).isNull();

	}

	@Test
	void testGetTestByQuizOrderByMarks() {
		testService.getTestByQuizOrderByMarks(new Quiz());
		verify(testRepository).findByQuizAndIsEndOrderByMarksDesc(any(Quiz.class), anyBoolean());
	}

	@Test
	void testGetNumberOfTestOfYear() {
		List<Long> listNumberOfTest = testService.getNumberOfTestOfYear(2023, null);
		verify(testRepository).findAll();
		assertThat(listNumberOfTest.size()).isEqualTo(12);

	}

	@Test
	void testGetNumberOfTest() {
		long numberOfTest = testService.getNumberOfTest(2023, new Quiz());
		verify(testRepository).findByQuizAndIsEnd(any(Quiz.class), anyBoolean());
		assertThat(numberOfTest).isEqualTo(0);
	}

}
