package vn.hcmuaf.edu.fit.quizbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hcmuaf.edu.fit.quizbackend.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);

	User findByEmail(String email);

}
