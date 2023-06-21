package vn.hcmuaf.edu.fit.quizbackend.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import vn.hcmuaf.edu.fit.quizbackend.model.exam.Category;
import vn.hcmuaf.edu.fit.quizbackend.repository.CategoryRepository;
import vn.hcmuaf.edu.fit.quizbackend.service.impl.CategoryServiceImpl;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

	@Mock
	private CategoryRepository categoryRepository;

	@InjectMocks
	private CategoryServiceImpl categoryService;

	@Test
	void testAddCategory() {
		Category category = initCategory();
		categoryService.addCategory(category);
		ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);
		verify(categoryRepository).save(categoryArgumentCaptor.capture());
		Category capturedCategory = categoryArgumentCaptor.getValue();
		assertThat(capturedCategory).isEqualTo(category);
	}

	@Test
	void testUpdateCategory() {
		Category category = initCategory();
		categoryService.updateCategory(category);
		ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);
		verify(categoryRepository).save(categoryArgumentCaptor.capture());
		Category capturedCategory = categoryArgumentCaptor.getValue();
		assertThat(capturedCategory).isEqualTo(category);
	}

	@Test
	void testGetCategories() {
		categoryService.getCategories();
		verify(categoryRepository).findAll();
	}

	@Test
	void testGetCategory() {
		categoryService.getCategory(anyLong());
		verify(categoryRepository).findById(anyLong());

	}

	@Test
	void testDeleteCategory() {
		categoryService.deleteCategory(anyLong());
		verify(categoryRepository,times(1)).deleteById(anyLong());
	}

	private Category initCategory() {
		Category category = new Category("123", "hay");
		return category;
	}

}
