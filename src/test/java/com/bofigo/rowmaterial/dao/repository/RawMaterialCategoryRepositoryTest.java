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

import com.bofigo.rowmaterial.dao.model.RawMaterialCategoryModel;
import com.bofigo.rowmaterial.util.RawMaterialCategoryRepositoryTestUtil;

public class RawMaterialCategoryRepositoryTest extends AbstractRepositoryTest {

	final RawMaterialCategoryModel model = RawMaterialCategoryRepositoryTestUtil.getModel();

	@Autowired
	private RawMaterialCategoryRepository rawMaterialCategoryRepository;

	@Autowired
	private EntityManager entityManager;

	@BeforeEach
	public void setUp() {
		entityManager.persist(model);
	}

	@Test
	public void givenId_whenFindById_thenShouldReturnModel() {
		Optional<RawMaterialCategoryModel> rawMaterialCategoryModel = rawMaterialCategoryRepository
				.findById(model.getId());
		assertTrue(rawMaterialCategoryModel.isPresent());
		assertNotNull(rawMaterialCategoryModel.get());
		assertEquals(model.getName(), rawMaterialCategoryModel.get().getName());
	}

	@Test
	public void givenId_whenFindById_thenShouldReturnNull() {
		Optional<RawMaterialCategoryModel> rawMaterialCategoryModel = rawMaterialCategoryRepository
				.findById(model.getId() + 10);
		assertFalse(rawMaterialCategoryModel.isPresent());
	}

	@Test
	public void givenName_whenFindByName_thenShouldReturnModel() {
		RawMaterialCategoryModel rawMaterialCategoryModel = rawMaterialCategoryRepository.findByName(model.getName());
		assertNotNull(rawMaterialCategoryModel);
		assertEquals(model.getName(), rawMaterialCategoryModel.getName());
	}

	@Test
	public void givenName_whenFindByName_thenShouldReturnNull() {
		RawMaterialCategoryModel rawMaterialCategoryModel = rawMaterialCategoryRepository
				.findByName(model.getName() + "_");
		assertNull(rawMaterialCategoryModel);
	}

	@Test
	public void givenId_whenDeleteById_thenShouldReturnNull() {
		rawMaterialCategoryRepository.deleteById(model.getId());
		Optional<RawMaterialCategoryModel> rawMaterialCategoryModel = rawMaterialCategoryRepository
				.findById(model.getId());
		assertFalse(rawMaterialCategoryModel.isPresent());
	}
	
	@Test
	public void givenId_whenDeleteAll_thenShouldReturnNull() {
		rawMaterialCategoryRepository.deleteAll();
		Optional<RawMaterialCategoryModel> rawMaterialCategoryModel = rawMaterialCategoryRepository
				.findById(model.getId());
		assertFalse(rawMaterialCategoryModel.isPresent());
	}


}
