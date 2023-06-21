package vn.hcmuaf.edu.fit.quizbackend.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import vn.hcmuaf.edu.fit.quizbackend.model.User;
import vn.hcmuaf.edu.fit.quizbackend.model.exam.Question;
import vn.hcmuaf.edu.fit.quizbackend.repository.UserRepository;
import vn.hcmuaf.edu.fit.quizbackend.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userService;

	@Test
	void testGetAllUser() {
		userService.getAllUser();
		verify(userRepository).findAll();
	}

	@Test
	void testCreateUser() throws Exception {
		User user = new User();
		userService.createUser(user, new HashSet<>());
		ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
		verify(userRepository).save(userArgumentCaptor.capture());
		User capturedUser = userArgumentCaptor.getValue();
		assertThat(capturedUser).isEqualTo(user);
	}

	@Test
	void testGetUser() {
		userService.getUser(anyString());
		verify(userRepository).findByUsername(anyString());
	}

	@Test
	void testGetUserById() {
		userService.getUserById(anyLong());
		verify(userRepository).findById(anyLong());
	}

	@Test
	void testDeleteUser() {
		userService.deleteUser(anyLong());
		verify(userRepository, times(1)).deleteById(anyLong());
	}

	@Test
	void testSaveUser() {
		User user = new User();
		userService.saveUser(user);
		verify(userRepository).save(any(User.class));
	}

	@Test
	void testUserStatistics() {
		List<Long> list = userService.userStatistics();
		verify(userRepository).findAll();
		assertThat(list.size()).isEqualTo(2);
	}

	@Test
	void testCountUser() {
		userService.countUser();
		verify(userRepository).count();
	}

	@Test
	void testDisableAccount() {
		User user = userService.disableAccount(anyLong());
		assertThat(user).isNull();
	}

	@Test
	void testEnableAccount() {
		User user = userService.enableAccount(anyLong());
		assertThat(user).isNull();
	}

	@Test
	void testAddRoleAdminToUser() {
		User user = userService.addRoleAdminToUser(anyLong());
		assertThat(user).isNull();
	}

}
