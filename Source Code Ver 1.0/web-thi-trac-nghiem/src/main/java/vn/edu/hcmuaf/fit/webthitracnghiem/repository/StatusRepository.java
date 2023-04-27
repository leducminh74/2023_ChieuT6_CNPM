package vn.edu.hcmuaf.fit.webthitracnghiem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.hcmuaf.fit.webthitracnghiem.entity.Status;


public interface StatusRepository extends JpaRepository<Status, Integer> {
	Status findBysName(String sName);
}
