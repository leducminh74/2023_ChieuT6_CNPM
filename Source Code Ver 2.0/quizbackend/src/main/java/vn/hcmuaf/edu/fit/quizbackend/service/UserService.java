package vn.hcmuaf.edu.fit.quizbackend.service;

import java.util.Set;

import vn.hcmuaf.edu.fit.quizbackend.model.User;
import vn.hcmuaf.edu.fit.quizbackend.model.UserRole;

public interface UserService {
	
//	create user
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;

//	get user by username
	public User getUser(String username);
	
//	delete user by id
	public void deleteUser(Long id);
	
}
