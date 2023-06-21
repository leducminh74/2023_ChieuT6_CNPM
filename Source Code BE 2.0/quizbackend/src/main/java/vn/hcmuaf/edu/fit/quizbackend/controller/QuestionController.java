package vn.hcmuaf.edu.fit.quizbackend.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hcmuaf.edu.fit.quizbackend.model.exam.Question;
import vn.hcmuaf.edu.fit.quizbackend.model.exam.Quiz;
import vn.hcmuaf.edu.fit.quizbackend.service.QuestionService;
import vn.hcmuaf.edu.fit.quizbackend.service.QuizService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuizService quizService;

	@PostMapping("/")
	public ResponseEntity<Question> addQuiz(@RequestBody Question question) {
		Question question1 = this.questionService.addQuestion(question);
		return ResponseEntity.ok(question1);
	}

	@PutMapping("/")
	public ResponseEntity<Question> update(@RequestBody Question question) {
		return ResponseEntity.ok(this.questionService.updateQuestion(question));
	}

	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getAllQuestionOfQuiz(@PathVariable("qid") Long qid) {
//		Quiz quiz = new Quiz();
//		quiz.setqId(qid);
//		Set<Question> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
//
//		return ResponseEntity.ok(questionsOfQuiz);

		Quiz quiz = this.quizService.getQuiz(qid).get();
		Set<Question> questions = quiz.getQuestions();
		List<Question> list = new ArrayList(questions);
		if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
			list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));

		}

		Collections.shuffle(list);
		return ResponseEntity.ok(list);
	}

	@GetMapping("/quiz/all/{qid}")
	public ResponseEntity<?> getAllQuestionOfQuizAdmin(@PathVariable("qid") Long qid) {
		Quiz quiz = new Quiz();
		quiz.setqId(qid);
		Set<Question> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);

		return ResponseEntity.ok(questionsOfQuiz);

	}

	@GetMapping("/{quesId}")
	public Question get(@PathVariable("quesId") Long quesId) {

		return this.questionService.getQuestion(quesId).get();
	}

	@DeleteMapping("/{quesId}")
	public void deleteQuestion(@PathVariable("quesId") Long quesId) {
		this.questionService.deleteQuestion(quesId);

	}

	@GetMapping("/")
	public ResponseEntity<?> getAllQuestion() {
		return ResponseEntity.ok(this.questionService.getQuestions());
	}

	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) {
		double marksGot = 0;
		int correctAnswer = 0;
		int attempted = 0;
		double maxMark = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks());
		for (Question question : questions) {

			Question question2 = this.questionService.get(question.getQuesId());
			if (question2.getAnswer().equals(question.getGivenAnswer())) {
				correctAnswer++;

				double marksSingle = Math
						.ceil(Double.parseDouble(questions.get(0).getQuiz().getMaxMarks()) / (questions.size()));
				marksGot += marksSingle;
			}
			if (question.getGivenAnswer() != null) {
				attempted++;
			}
		}
		Map<String, Object> map = Map.of("marksGot", marksGot, "correctAnswer", correctAnswer, "attempted", attempted,
				"maxMarks", maxMark, "totalQuestion", questions.size());
		return ResponseEntity.ok(map);
	}
}
