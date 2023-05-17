package vn.hcmuaf.edu.fit.quizbackend.helper;

public class UserFoundException extends Exception{
	public UserFoundException() {
		super("Username with this username is already exist ");
	}
	
	public UserFoundException(String msg) {
		super(msg);
	}
}
