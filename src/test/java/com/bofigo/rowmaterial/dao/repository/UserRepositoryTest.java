package com.bofigo.rowmaterial.dao.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bofigo.rowmaterial.dao.model.UserModel;
import com.bofigo.rowmaterial.util.UserRepositoryTestUtil;

public class UserRepositoryTest extends AbstractRepositoryTest {

	final UserModel model = UserRepositoryTestUtil.getModel();

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EntityManager entityManager;

	@BeforeEach
	public void setUp() {
		entityManager.persist(model);
	}

	@Test
	public void givenId_whenFindById_thenShouldReturnModel() {
		Optional<UserModel> userModel = userRepository
				.findById(model.getId());
		assertTrue(userModel.isPresent());
		assertNotNull(userModel.get());
		assertEquals(model.getName(), userModel.get().getName());
	}

	@Test
	public void givenId_whenFindById_thenShouldReturnNull() {
		Optional<UserModel> userModel = userRepository
				.findById(model.getId() + 10);
		assertFalse(userModel.isPresent());
	}

	@Test
	public void givenName_whenFindByUsername_thenShouldReturnModel() {
		UserModel userModel = userRepository.findByUsername(model.getUsername());
		assertNotNull(userModel);
		assertEquals(model.getName(), userModel.getName());
	}

	@Test
	public void givenName_whenFindByUsername_thenShouldReturnNull() {
		UserModel userModel = userRepository
				.findByUsername(model.getUsername() + "_");
		assertNull(userModel);
	}

	@Test
	public void givenId_whenDeleteById_thenShouldReturnNull() {
		userRepository.deleteById(model.getId());
		Optional<UserModel> userModel = userRepository
				.findById(model.getId());
		assertFalse(userModel.isPresent());
	}
	
	@Test
	public void givenId_whenDeleteAll_thenShouldReturnNull() {
		userRepository.deleteAll();
		Optional<UserModel> userModel = userRepository
				.findById(model.getId());
		assertFalse(userModel.isPresent());
	}


}
