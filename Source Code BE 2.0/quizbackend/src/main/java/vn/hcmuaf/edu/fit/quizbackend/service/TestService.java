package vn.hcmuaf.edu.fit.quizbackend.service;

import java.util.List;
import java.util.Set;

import vn.hcmuaf.edu.fit.quizbackend.model.Test;
import vn.hcmuaf.edu.fit.quizbackend.model.User;
import vn.hcmuaf.edu.fit.quizbackend.model.exam.Quiz;

public interface TestService {

	public List<Test> getTestByUser(User user);

	public Test startTest(Test test);

	public Test endTest(Test test);

	public List<Test> getTestByQuizOrderByMarks(Quiz quiz);

	public List<Long> getNumberOfTestOfYear(int year, Quiz quiz);

	public long getNumberOfTest(int year,Quiz quiz);

}
