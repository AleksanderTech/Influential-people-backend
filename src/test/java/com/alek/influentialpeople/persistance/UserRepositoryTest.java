package com.alek.influentialpeople.persistance;

import com.alek.influentialpeople.user.persistence.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void whenFindByName_thenReturnEmployee() {
		// given
//		User alex = new User(1, "Olek", "olek50000@o2.pl", "abcd", 0, "USER", ZonedDateTime.now());
//		entityManager.persist(alex);
//		entityManager.flush();
//
//		// when
//		User found = userRepository.findByNickname(alex.getNickname());
//		// then
//		assertThat(found.getNickname()).isEqualTo(alex.getNickname());
	}

}
