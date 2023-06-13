package vn.hcmuaf.edu.fit.quizbackend.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import vn.hcmuaf.edu.fit.quizbackend.model.exam.Quiz;

@Entity
@Table(name = "test")
public class Test {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties(value = { "tests", "handler", "hibernateLazyInitializer" }, allowSetters = true)
	private User user;

	@ManyToOne
	@JoinColumn(name = "quiz_id")
	@JsonIgnoreProperties(value = { "tests", "handler", "hibernateLazyInitializer" }, allowSetters = true)
	private Quiz quiz;

	private int numberOfQuestionCorrect;
	private int numberOfQuestionAttempted;
	private double marks;

	@CreationTimestamp
	private Timestamp timeStart;

	@UpdateTimestamp
	private Timestamp timeEnd;
	private Long examTime;
	private int numberOfTest;
	private boolean isEnd = false;

	public Test() {

	}

	public Test(User user, Quiz quiz, int numberOfQuestionCorrect, int numberOfQuestionAttempted, double marks,
			Timestamp timeStart, Timestamp timeEnd, Long examTime, int numberOfTest) {

		this.user = user;
		this.quiz = quiz;
		this.numberOfQuestionCorrect = numberOfQuestionCorrect;
		this.numberOfQuestionAttempted = numberOfQuestionAttempted;
		this.marks = marks;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.examTime = examTime;
		this.numberOfTest = numberOfTest;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public int getNumberOfQuestionCorrect() {
		return numberOfQuestionCorrect;
	}

	public void setNumberOfQuestionCorrect(int numberOfQuestionCorrect) {
		this.numberOfQuestionCorrect = numberOfQuestionCorrect;
	}

	public int getNumberOfQuestionAttempted() {
		return numberOfQuestionAttempted;
	}

	public void setNumberOfQuestionAttempted(int numberOfQuestionAttempted) {
		this.numberOfQuestionAttempted = numberOfQuestionAttempted;
	}

	public double getMarks() {
		return marks;
	}

	public void setMarks(double marks) {
		this.marks = marks;
	}

	public Timestamp getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Timestamp timeStart) {
		this.timeStart = timeStart;
	}

	public Timestamp getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Timestamp timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Long getExamTime() {
		return examTime;
	}

	public void setExamTime(Long examTime) {
		this.examTime = examTime;
	}

	public int getNumberOfTest() {
		return numberOfTest;
	}

	public void setNumberOfTest(int numberOfTest) {
		this.numberOfTest = numberOfTest;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

}
