package vn.hcmuaf.edu.fit.quizbackend.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import vn.hcmuaf.edu.fit.quizbackend.model.exam.Category;
import vn.hcmuaf.edu.fit.quizbackend.model.exam.Quiz;
import vn.hcmuaf.edu.fit.quizbackend.repository.QuizRepository;
import vn.hcmuaf.edu.fit.quizbackend.service.impl.QuizServiceImpl;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

	@Mock
	private QuizRepository quizRepository;

	@InjectMocks
	private QuizServiceImpl quizService;

	@Test
	void testAddQuiz() {
		Quiz quiz = new Quiz();
		quizService.addQuiz(quiz);
		ArgumentCaptor<Quiz> quizArgumentCaptor = ArgumentCaptor.forClass(Quiz.class);
		verify(quizRepository).save(quizArgumentCaptor.capture());
		Quiz capturedQuiz = quizArgumentCaptor.getValue();
		assertThat(capturedQuiz).isEqualTo(quiz);
	}

	@Test
	void testUpdateQuiz() {
		Quiz quiz = new Quiz();
		quizService.updateQuiz(quiz);
		ArgumentCaptor<Quiz> quizArgumentCaptor = ArgumentCaptor.forClass(Quiz.class);
		verify(quizRepository).save(quizArgumentCaptor.capture());
		Quiz capturedQuiz = quizArgumentCaptor.getValue();
		assertThat(capturedQuiz).isEqualTo(quiz);
	}

	@Test
	void testGetQuizzes() {
		quizService.getQuizzes();
		verify(quizRepository).findAll();
	}

	@Test
	void testGetQuiz() {
		quizService.getQuiz(anyLong());
		verify(quizRepository).findById(anyLong());
	}

	@Test
	void testDeleteQuiz() {
		quizService.deleteQuiz(anyLong());
		verify(quizRepository, times(1)).deleteById(anyLong());
	}

	@Test
	void testGetQuizzesOfCategory() {
		quizService.getQuizzesOfCategory(new Category());
		verify(quizRepository).findByCategory(any(Category.class));
	}

	@Test
	void testGetActiveQuizzes() {
		quizService.getActiveQuizzes();
		verify(quizRepository).findByActive(anyBoolean());
	}

	@Test
	void testGetActiveQuizzesOfCategory() {
		quizService.getActiveQuizzesOfCategory(new Category());
		verify(quizRepository).findByCategoryAndActive(any(Category.class), anyBoolean());
	}

}
