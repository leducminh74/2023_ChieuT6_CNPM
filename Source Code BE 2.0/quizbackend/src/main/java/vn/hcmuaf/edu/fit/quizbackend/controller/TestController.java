package vn.hcmuaf.edu.fit.quizbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.hcmuaf.edu.fit.quizbackend.model.Test;
import vn.hcmuaf.edu.fit.quizbackend.model.User;
import vn.hcmuaf.edu.fit.quizbackend.model.exam.Quiz;
import vn.hcmuaf.edu.fit.quizbackend.service.QuizService;
import vn.hcmuaf.edu.fit.quizbackend.service.TestService;
import vn.hcmuaf.edu.fit.quizbackend.service.UserService;

@RestController
@RequestMapping("/test")
@CrossOrigin("*")
public class TestController {

	@Autowired
	private TestService testService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private QuizService quizService;
	
//	get list test order by exam time
	@GetMapping("/{qid}")
	public ResponseEntity<List<Test>> getTestByQuizOrderByMarks(@PathVariable Long qid) {
		Quiz quiz = quizService.getQuiz(qid);
		List<Test> list = testService.getTestByQuizOrderByMarks(quiz);
		return ResponseEntity.ok(list);
	}


//	get list test by user
	@GetMapping("/")
	public ResponseEntity<List<Test>> getListTestByUser(@RequestParam Long userId) {
		User user = userService.getUserById(userId);
		List<Test> list = testService.getTestByUser(user);
		return ResponseEntity.ok(list);
	}

//	start test
	@PostMapping("/")
	public ResponseEntity<Test> startTest(@RequestBody Test test) {
		return ResponseEntity.ok(testService.startTest(test));
	}

//	end test
	@PutMapping("/")
	public ResponseEntity<Test> endTest(@RequestBody Test test) {
		return ResponseEntity.ok(testService.endTest(test));
	}

}
