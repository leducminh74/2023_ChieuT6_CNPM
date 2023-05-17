package vn.hcmuaf.edu.fit.quizbackend.helper;

public class UserNotFoundException extends Exception {


	public UserNotFoundException() {
		super("User with this Username is already there in DB !! try with another User");
	}

	public UserNotFoundException(String msg) {
		super(msg);
	}
}
