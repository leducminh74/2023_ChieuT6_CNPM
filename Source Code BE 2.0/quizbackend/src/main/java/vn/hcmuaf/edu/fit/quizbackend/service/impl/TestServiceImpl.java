package vn.hcmuaf.edu.fit.quizbackend.service.impl;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.hcmuaf.edu.fit.quizbackend.model.Test;
import vn.hcmuaf.edu.fit.quizbackend.model.User;
import vn.hcmuaf.edu.fit.quizbackend.model.exam.Quiz;
import vn.hcmuaf.edu.fit.quizbackend.repository.TestRepository;
import vn.hcmuaf.edu.fit.quizbackend.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestRepository testRepository;

	@Override
	public List<Test> getTestByUser(User user) {
		return testRepository.findByUserAndIsEnd(user, true);
	}

	@Override
	public Test startTest(Test test) {
		return testRepository.save(test);
	}

	@Override
	public Test endTest(Test test) {
		Test t = testRepository.findById(test.getId()).get();
		t.setNumberOfQuestionAttempted(test.getNumberOfQuestionAttempted());
		t.setNumberOfQuestionCorrect(test.getNumberOfQuestionCorrect());
		t.setMarks(test.getMarks());
		t.setEnd(true);
		Test t1 = testRepository.save(t);
		t1.setExamTime(
				Duration.between(t.getTimeStart().toLocalDateTime(), t.getTimeEnd().toLocalDateTime()).getSeconds());
		return testRepository.save(t1);
	}

	@Override
	public List<Test> getTestByQuizOrderByMarks(Quiz quiz) {
		List<Test> listResult = new ArrayList<>();
		List<Test> list = testRepository.findByQuizAndIsEndOrderByMarksDesc(quiz, true);
		List<Test> addList = new ArrayList<>();
		List<Test> removeList = new ArrayList<>();
		for (Test test : list) {
			if (listResult.isEmpty()) {
				listResult.add(test);
			} else {
				if (!listResult.contains(test)) {
					if (existUser(listResult, test) != null) {
						if (test.getMarks() > existUser(listResult, test).getMarks()) {
							addList.add(test);
							removeList.add(existUser(listResult, test));
						} else if (test.getMarks() == existUser(listResult, test).getMarks()) {
							if (test.getExamTime() < existUser(listResult, test).getExamTime()) {
								addList.add(test);
								removeList.add(existUser(listResult, test));
							}
						}
					} else if (existUser(listResult, test) == null) {
						addList.add(test);
					}

					listResult.removeAll(removeList);
					listResult.addAll(addList);
					removeList.clear();
					addList.clear();

				}
			}

		}

		return listResult;
	}

	private Test existUser(List<Test> list, Test t) {
		for (Test test : list) {
			if (t.getUser().getId() == test.getUser().getId()) {
				return test;
			}
		}
		return null;
	}

	@Override
	public List<Long> getNumberOfTestOfYear(int year, Quiz quiz) {
		List<Test> list = new ArrayList<>();
		List<Long> longList = new ArrayList<>();
		long countNumberOfTest = 0;
		if (quiz == null) {
			list = testRepository.findAll();
		} else {
			list = testRepository.findByQuizAndIsEnd(quiz, true);
		}
		for (int i = 0; i < 12; i++) {
			if (list != null) {
				for (Test test : list) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(test.getTimeStart());
					if (cal.get(Calendar.MONTH) == i && cal.get(Calendar.YEAR) == year && test.isEnd()) {
						countNumberOfTest += 1;
					}
				}
				longList.add(countNumberOfTest);
				countNumberOfTest = 0;
			} else {
				longList.add(countNumberOfTest);
			}
		}
		return longList;

	}

	@Override
	public long getNumberOfTest(int year, Quiz quiz) {
		List<Test> list = new ArrayList<>();
		long count = 0;
		if (quiz == null) {
			list = testRepository.findAll();
		} else {
			list = testRepository.findByQuizAndIsEnd(quiz, true);
		}
		for (Test test : list) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(test.getTimeStart());
			if (cal.get(Calendar.YEAR) == year && test.isEnd()) {
				count += 1;
			}
		}

		return count;
	}

}
