package vn.hcmuaf.edu.fit.quizbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hcmuaf.edu.fit.quizbackend.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}
