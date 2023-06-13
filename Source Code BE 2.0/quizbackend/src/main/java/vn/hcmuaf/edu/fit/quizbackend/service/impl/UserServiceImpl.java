package vn.hcmuaf.edu.fit.quizbackend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.hcmuaf.edu.fit.quizbackend.helper.UserFoundException;
import vn.hcmuaf.edu.fit.quizbackend.model.Role;
import vn.hcmuaf.edu.fit.quizbackend.model.User;
import vn.hcmuaf.edu.fit.quizbackend.model.UserRole;
import vn.hcmuaf.edu.fit.quizbackend.repository.RoleRepository;
import vn.hcmuaf.edu.fit.quizbackend.repository.UserRepository;
import vn.hcmuaf.edu.fit.quizbackend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	
//	get all user
	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

//	creating user
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		User local = this.userRepository.findByUsername(user.getUsername());
		if (local != null) {
			System.out.println("User is already there !!");
			throw new UserFoundException();
		} else {
			for (UserRole userRole : userRoles) {
				roleRepository.save(userRole.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			local = this.userRepository.save(user);
		}
		return local;
	}

//	getting user by username
	@Override
	public User getUser(String username) {

		return this.userRepository.findByUsername(username);
	}

//	getting user by id
	@Override
	public User getUserById(Long id) {

		return this.userRepository.findById(id).get();
	}

//	delete user by id
	@Override
	public void deleteUser(Long id) {
		this.userRepository.deleteById(id);

	}

	@Override
	public User saveUser(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public List<Long> userStatistics() {
		long activeAccount = 0;
		long notActiveAccount = 0;
		List<User> users = userRepository.findAll();
		List<Long> list = new ArrayList<>();
		for (User user : users) {
			if (!user.isEnable()) {
				notActiveAccount += 1;
			}
			if (user.isEnable()) {
				activeAccount += 1;
			}
		}
		list.add(notActiveAccount);
		list.add(activeAccount);

		return list;
	}

	@Override
	public Long countUser() {
		return userRepository.count();
	}

	@Override
	public User disableAccount(Long id) {
		User user = getUserById(id);
		user.setEnable(false);
		return saveUser(user);
	}

	@Override
	public User enableAccount(Long id) {
		User user = getUserById(id);
		user.setEnable(true);
		return saveUser(user);
	}

	@Override
	public User addRoleAdminToUser(Long id) {
		User user = getUserById(id);
		Set<UserRole> roles = user.getUserRoles();
		UserRole userRole = new UserRole();
		Role admin = roleRepository.findById((long) 46).get();
		if (checkExist(roles, admin) == null) {
			userRole.setRole(admin);
			userRole.setUser(user);
			roles.add(userRole);
			user.getUserRoles().addAll(roles);
		}
		return saveUser(user);

	}

	private UserRole checkExist(Set<UserRole> roles, Role role) {
		for (UserRole userRole : roles) {
			if (userRole.getRole().getRoleId() == role.getRoleId()) {
				return userRole;
			}
		}
		return null;
	}

}
