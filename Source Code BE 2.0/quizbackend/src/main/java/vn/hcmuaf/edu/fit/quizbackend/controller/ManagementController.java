package vn.hcmuaf.edu.fit.quizbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.hcmuaf.edu.fit.quizbackend.model.exam.Quiz;
import vn.hcmuaf.edu.fit.quizbackend.service.QuizService;
import vn.hcmuaf.edu.fit.quizbackend.service.TestService;
import vn.hcmuaf.edu.fit.quizbackend.service.UserService;

@RestController
@RequestMapping("/management")
@CrossOrigin("*")
public class ManagementController {

	@Autowired
	private TestService testService;

	@Autowired
	private QuizService quizService;

	@Autowired
	private UserService userService;

	@GetMapping("/numberOfTests")
	public ResponseEntity<?> getListNumberOfTestsOfYear(
			@RequestParam(value = "year", defaultValue = "2023", required = false) int year,
			@RequestParam(value = "qid", defaultValue = "0", required = false) long qid) {

		List<Long> list = new ArrayList<>();
		if (qid == 0) {
			list = testService.getNumberOfTestOfYear(year, null);
		} else {
			Quiz quiz = quizService.getQuiz(qid).get();
			list = testService.getNumberOfTestOfYear(year, quiz);
		}
		return ResponseEntity.ok(list);
	}

	@GetMapping("/user-statistics")
	public ResponseEntity<?> getListUserStatistics() {
		List<Long> list = userService.userStatistics();
		return ResponseEntity.ok(list);

	}

	@GetMapping("/total-numberOfTest-ofYear")
	public ResponseEntity<?> totalNumberOfTestOfYear(
			@RequestParam(value = "year", defaultValue = "2023", required = false) int year,
			@RequestParam(value = "qid", defaultValue = "0", required = false) long qid) {

		long total = 0;
		if (qid == 0) {
			total = testService.getNumberOfTest(year, null);
		} else {
			Quiz quiz = quizService.getQuiz(qid).get();
			total = testService.getNumberOfTest(year, quiz);
		}

		return ResponseEntity.ok(total);
	}
	
	@GetMapping("/countTotalUser")
	public ResponseEntity<?> countTotalUser() {
		long total = userService.countUser();
		return ResponseEntity.ok(total);
	}

}
