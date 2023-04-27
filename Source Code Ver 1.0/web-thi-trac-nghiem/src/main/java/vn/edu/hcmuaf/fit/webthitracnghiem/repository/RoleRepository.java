package vn.edu.hcmuaf.fit.webthitracnghiem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.hcmuaf.fit.webthitracnghiem.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findByroleName(String roleName);
}
