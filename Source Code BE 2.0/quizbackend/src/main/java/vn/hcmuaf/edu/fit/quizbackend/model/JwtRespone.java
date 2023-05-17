package vn.hcmuaf.edu.fit.quizbackend.model;

public class JwtRespone {
	private String token;

	public JwtRespone(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
