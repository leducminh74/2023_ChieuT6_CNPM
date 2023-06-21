package vn.hcmuaf.edu.fit.quizbackend.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.hcmuaf.edu.fit.quizbackend.model.exam.Category;
import vn.hcmuaf.edu.fit.quizbackend.repository.CategoryRepository;
import vn.hcmuaf.edu.fit.quizbackend.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category addCategory(Category category) {
		return this.categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category) {

		return this.categoryRepository.save(category);
	}

	@Override
	public Set<Category> getCategories() {
		return new HashSet<>(this.categoryRepository.findAll());
	}

	@Override
	public Optional<Category> getCategory(Long categoryId) {
		
		return this.categoryRepository.findById(categoryId);
	}

	@Override
	public void deleteCategory(Long categoryId) {
		this.categoryRepository.deleteById(categoryId);

	}

}
