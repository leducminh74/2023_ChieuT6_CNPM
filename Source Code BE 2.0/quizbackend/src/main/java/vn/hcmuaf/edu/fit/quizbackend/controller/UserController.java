package vn.hcmuaf.edu.fit.quizbackend.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hcmuaf.edu.fit.quizbackend.helper.UpdateUserRequest;
import vn.hcmuaf.edu.fit.quizbackend.helper.UserFoundException;
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

//	get all user
	@GetMapping("/getAllUser")
	public ResponseEntity<List<User>> getAllUser() {
		return ResponseEntity.ok(userService.getAllUser());
	}

//	creating user
	@PostMapping("/")
	public User createUser(@RequestBody @Valid User user, BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			throw new Exception("Invalid value");
		}
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

//	update user
	@PutMapping("/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody @Valid UpdateUserRequest userRequest,BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()) {
			throw new Exception("Invalid value");
		}
		User user = userService.getUserById(id).get();
		user.setEmail(userRequest.getEmail());
		user.setFirstName(userRequest.getFirstName());
		user.setLastName(userRequest.getLastName());
		user.setPhone(userRequest.getPhone());

		return this.userService.saveUser(user);
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

//	disable user
	@GetMapping("/disable/{id}")
	public ResponseEntity<User> disableAccount(@PathVariable Long id) {
		User user = userService.disableAccount(id);
		if (user != null) {
			return ResponseEntity.ok(user);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

//	enable user
	@GetMapping("/enable/{id}")
	public ResponseEntity<User> enableAccount(@PathVariable Long id) {
		User user = userService.enableAccount(id);
		if (user != null) {
			return ResponseEntity.ok(user);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

//	add role admin to user
	@GetMapping("/addAdmin/{id}")
	public ResponseEntity<User> addRoleAdminToUser(@PathVariable Long id) {
		User user = userService.addRoleAdminToUser(id);
		if (user != null) {
			return ResponseEntity.ok(user);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserFoundException.class)
	public ResponseEntity<?> exceptionHandle(UserFoundException e) {
		return ResponseEntity.ok(e);
	}

}
