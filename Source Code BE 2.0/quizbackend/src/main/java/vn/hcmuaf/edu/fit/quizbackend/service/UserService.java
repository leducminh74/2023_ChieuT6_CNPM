package vn.hcmuaf.edu.fit.quizbackend.service;

import java.util.List;
import java.util.Set;

import vn.hcmuaf.edu.fit.quizbackend.model.User;
import vn.hcmuaf.edu.fit.quizbackend.model.UserRole;

public interface UserService {

//	getAllUser
	public List<User> getAllUser();

//	create user
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;

//	get user by username
	public User getUser(String username);

//	get user by id
	public User getUserById(Long id);

//	delete user by id
	public void deleteUser(Long id);

//	save user
	public User saveUser(User user);

	public List<Long> userStatistics();

	public Long countUser();

	public User disableAccount(Long id);

	public User enableAccount(Long id);

	public User addRoleAdminToUser(Long id);

}
