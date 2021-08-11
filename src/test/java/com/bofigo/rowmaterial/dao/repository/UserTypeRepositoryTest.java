package com.bofigo.rowmaterial.dao.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bofigo.rowmaterial.dao.model.UserTypeModel;
import com.bofigo.rowmaterial.util.UserTypeRepositoryTestUtil;

public class UserTypeRepositoryTest extends AbstractRepositoryTest {

	final UserTypeModel model = UserTypeRepositoryTestUtil.getModel();

	@Autowired
	private UserTypeRepository userTypeRepository;

	@Autowired
	private EntityManager entityManager;

	@BeforeEach
	public void setUp() {
		entityManager.persist(model);
	}

	@Test
	public void givenId_whenFindById_thenShouldReturnModel() {
		Optional<UserTypeModel> userTypeModel = userTypeRepository
				.findById(model.getId());
		assertTrue(userTypeModel.isPresent());
		assertNotNull(userTypeModel.get());
		assertEquals(model.getName(), userTypeModel.get().getName());
	}

	@Test
	public void givenId_whenFindById_thenShouldReturnNull() {
		Optional<UserTypeModel> userTypeModel = userTypeRepository
				.findById(model.getId() + 10);
		assertFalse(userTypeModel.isPresent());
	}

	@Test
	public void givenId_whenDeleteById_thenShouldReturnNull() {
		userTypeRepository.deleteById(model.getId());
		Optional<UserTypeModel> userTypeModel = userTypeRepository
				.findById(model.getId());
		assertFalse(userTypeModel.isPresent());
	}
	
	@Test
	public void givenId_whenDeleteAll_thenShouldReturnNull() {
		userTypeRepository.deleteAll();
		Optional<UserTypeModel> userTypeModel = userTypeRepository
				.findById(model.getId());
		assertFalse(userTypeModel.isPresent());
	}


}
