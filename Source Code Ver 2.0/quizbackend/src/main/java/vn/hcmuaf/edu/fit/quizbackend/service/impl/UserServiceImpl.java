package vn.hcmuaf.edu.fit.quizbackend.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.hcmuaf.edu.fit.quizbackend.helper.UserFoundException;
import vn.hcmuaf.edu.fit.quizbackend.helper.UserNotFoundException;
import vn.hcmuaf.edu.fit.quizbackend.model.User;
import vn.hcmuaf.edu.fit.quizbackend.model.UserRole;
import vn.hcmuaf.edu.fit.quizbackend.repository.RoleRepository;
import vn.hcmuaf.edu.fit.quizbackend.repository.UserRepository;
import vn.hcmuaf.edu.fit.quizbackend.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
//	creating user
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		User local = this.userRepository.findByUsername(user.getUsername());
		if(local != null) {
			System.out.println("User is already there !!");
			throw new UserFoundException();
		}else {
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
	
//	delete user by id
	@Override
	public void deleteUser(Long id) {
		this.userRepository.deleteById(id);
		
	}

}
