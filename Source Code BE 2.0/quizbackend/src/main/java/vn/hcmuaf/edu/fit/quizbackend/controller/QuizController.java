package vn.hcmuaf.edu.fit.quizbackend.controller;

import java.util.List;
import java.util.Map;

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

import vn.hcmuaf.edu.fit.quizbackend.model.exam.Category;
import vn.hcmuaf.edu.fit.quizbackend.model.exam.Question;
import vn.hcmuaf.edu.fit.quizbackend.model.exam.Quiz;
import vn.hcmuaf.edu.fit.quizbackend.service.QuestionService;
import vn.hcmuaf.edu.fit.quizbackend.service.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

	@Autowired
	private QuizService quizService;
	
	@Autowired
	private QuestionService questionService;

	@PostMapping("/")
	public ResponseEntity<Quiz> add(@RequestBody Quiz quiz) {
		return ResponseEntity.ok(this.quizService.addQuiz(quiz));
	}

	@PutMapping("/")
	public ResponseEntity<Quiz> update(@RequestBody Quiz quiz) {
		return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
	}

	@GetMapping("/")
	public ResponseEntity<?> quizzes() {
		return ResponseEntity.ok(this.quizService.getQuizzes());

	}

	@GetMapping("/{qid}")
	public Quiz quiz(@PathVariable("qid") Long qid) {
		return this.quizService.getQuiz(qid);
	}

	@DeleteMapping("/{qid}")
	public void delete(@PathVariable("qid") Long qid) {
		this.quizService.deleteQuiz(qid);
	}
	
	@GetMapping("/category/{cid}")
	public ResponseEntity<?> getQuizzesOfCategory(@PathVariable("cid") Long cid){
		Category category = new Category();
		category.setId(cid);
		return ResponseEntity.ok(this.quizService.getQuizzesOfCategory(category));
	}
	
	@GetMapping("/active")
	public List<Quiz> getActiveQuizzes(){
		return this.quizService.getActiveQuizzes();
		
	}
	
	@GetMapping("/category/active/{cid}")
	public List<Quiz> getActiveQuizzesOfCategory(@PathVariable("cid") Long cid){
		Category category = new Category();
		category.setId(cid);
		return this.quizService.getActiveQuizzesOfCategory(category);
	}
	
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions){
		double marksGot = 0;
		int correctAnswer = 0;
		int attempted = 0;	
		double maxMark = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks());
		for (Question question : questions) {
			
			Question question2 = this.questionService.get(question.getQuesId());
			if(question2.getAnswer().equals(question.getGivenAnswer())) {
				correctAnswer++;
				
				double marksSingle = Math.ceil(Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/(questions.size()));
				marksGot += marksSingle;
			}
			if(question.getGivenAnswer() != null) {
				attempted++;
			}
		}
		Map<String, Object> map = Map.of("marksGot",marksGot,"correctAnswer",correctAnswer,"attempted",attempted,"maxMarks",maxMark,"totalQuestion",questions.size());
		return ResponseEntity.ok(map);
	}

}
