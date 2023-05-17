package vn.hcmuaf.edu.fit.quizbackend.model;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority{
	
	private String authoriry;

	
	public Authority(String authoriry) {
		this.authoriry = authoriry;
	}

	@Override
	public String getAuthority() {
		return this.authoriry;
	}

}
