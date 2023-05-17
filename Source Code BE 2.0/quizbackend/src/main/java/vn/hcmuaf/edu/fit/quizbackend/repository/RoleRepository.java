package vn.hcmuaf.edu.fit.quizbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hcmuaf.edu.fit.quizbackend.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
}
