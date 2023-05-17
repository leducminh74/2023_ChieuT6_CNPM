package vn.hcmuaf.edu.fit.quizbackend.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.hcmuaf.edu.fit.quizbackend.config.JwtUtil;
import vn.hcmuaf.edu.fit.quizbackend.model.JwtRequest;
import vn.hcmuaf.edu.fit.quizbackend.model.JwtRespone;
import vn.hcmuaf.edu.fit.quizbackend.model.User;
import vn.hcmuaf.edu.fit.quizbackend.service.impl.UserDetailsServiceImpl;

@RestController
@CrossOrigin("*")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		try {
			authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User not found");
		}
		
		UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtRespone(token));
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER DISABLED");
		}catch (BadCredentialsException e) {
			throw new Exception("Invalid Credentials " + e.getMessage());
		}
	}
	
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		return (User) this.userDetailsServiceImpl.loadUserByUsername(principal.getName());
	}

}
