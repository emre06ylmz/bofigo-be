package com.bofigo.rowmaterial.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bofigo.rowmaterial.dao.model.UserTypeModel;

@Repository
public interface UserTypeRepository extends JpaRepository<UserTypeModel, Integer>, JpaSpecificationExecutor<UserTypeModel> {

	@Override
	Optional<UserTypeModel> findById(Integer id);

	UserTypeModel findByName(String name);

	@Override
	void deleteAll();
	
}
