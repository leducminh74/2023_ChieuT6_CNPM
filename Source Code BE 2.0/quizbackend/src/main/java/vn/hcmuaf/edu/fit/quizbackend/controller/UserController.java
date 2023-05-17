package vn.hcmuaf.edu.fit.quizbackend.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hcmuaf.edu.fit.quizbackend.helper.UserFoundException;
import vn.hcmuaf.edu.fit.quizbackend.helper.UserNotFoundException;
import vn.hcmuaf.edu.fit.quizbackend.model.Role;
import vn.hcmuaf.edu.fit.quizbackend.model.User;
import vn.hcmuaf.edu.fit.quizbackend.model.UserRole;
import vn.hcmuaf.edu.fit.quizbackend.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;

//	creating user
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		user.setProfile("default.png");
		
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		Set<UserRole> roles = new HashSet<>();
		Role role = new Role(); 
		role.setRoleId(45L);
		role.setRoleName("NORMAL");
		
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		roles.add(userRole);
		
		return this.userService.createUser(user, roles);
	}
	
//	get user by username
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {	
		return this.userService.getUser(username);
	}
	
//	delete user by id
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") Long id) {
		this.userService.deleteUser(id);
	}
	
	@ExceptionHandler(UserFoundException.class)
	public ResponseEntity<?> exceptionHandle(UserFoundException e){
		return ResponseEntity.ok(e);
	}
	
	
}
