package vn.hcmuaf.edu.fit.quizbackend.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import vn.hcmuaf.edu.fit.quizbackend.model.User;

@DataJpaTest
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	void testFindByUsername() {
		User user = initUser();
		userRepository.save(user);
		User expected = userRepository.findByUsername(user.getUsername());
		assertThat(user.getId()).isEqualTo(expected.getId());
	}

	@Test
	void testFindByEmail() {
		User user = initUser();
		userRepository.save(user);
		User expected = userRepository.findByEmail(user.getEmail());
		assertThat(user.getId()).isEqualTo(expected.getId());
	}

	private User initUser() {
		User user = new User();
		user.setUsername("123");
		user.setEmail("123@gmail.com");
		user.setFirstName("123");
		user.setLastName("123");
		user.setPassword("123");
		user.setPhone("1123");
		return user;
	}

}
