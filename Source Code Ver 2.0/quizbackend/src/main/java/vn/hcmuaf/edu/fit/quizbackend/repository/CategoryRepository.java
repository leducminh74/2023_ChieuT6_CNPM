package vn.hcmuaf.edu.fit.quizbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hcmuaf.edu.fit.quizbackend.model.exam.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
