package vn.hcmuaf.edu.fit.quizbackend.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import vn.hcmuaf.edu.fit.quizbackend.model.exam.Question;
import vn.hcmuaf.edu.fit.quizbackend.model.exam.Quiz;
import vn.hcmuaf.edu.fit.quizbackend.repository.QuestionRepository;
import vn.hcmuaf.edu.fit.quizbackend.service.impl.QuestionServiceImpl;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

	@Mock
	private QuestionRepository questionRepository;

	@InjectMocks
	private QuestionServiceImpl questionService;

	@Test
	void testAddQuestion() {
		Question question = initQuestion();
		questionService.addQuestion(question);
		ArgumentCaptor<Question> questionArgumentCaptor = ArgumentCaptor.forClass(Question.class);
		verify(questionRepository).save(questionArgumentCaptor.capture());
		Question capturedQuestion = questionArgumentCaptor.getValue();
		assertThat(capturedQuestion).isEqualTo(question);
	}

	@Test
	void testUpdateQuestion() {
		Question question = initQuestion();
		questionService.updateQuestion(question);
		ArgumentCaptor<Question> questionArgumentCaptor = ArgumentCaptor.forClass(Question.class);
		verify(questionRepository).save(questionArgumentCaptor.capture());
		Question capturedQuestion = questionArgumentCaptor.getValue();
		assertThat(capturedQuestion).isEqualTo(question);
	}

	@Test
	void testGetQuestions() {
		questionService.getQuestions();
		verify(questionRepository).findAll();
	}

	@Test
	void testGetQuestion() {
		questionService.getQuestion(anyLong());
		verify(questionRepository).findById(anyLong());
	}

	@Test
	final void testGetQuestionsOfQuiz() {
		questionService.getQuestionsOfQuiz(new Quiz());
		verify(questionRepository).findByQuiz(any(Quiz.class));
	}

	@Test
	void testDeleteQuestion() {
		questionService.deleteQuestion(anyLong());
		verify(questionRepository, times(1)).deleteById(anyLong());
	}

	private Question initQuestion() {
		Question question = new Question();
		return question;
	}

}
