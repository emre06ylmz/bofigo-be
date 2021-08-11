package com.bofigo.rowmaterial.domain.service;

import java.util.List;

import com.bofigo.rowmaterial.domain.dto.UserTypeServiceInput;
import com.bofigo.rowmaterial.domain.dto.UserTypeServiceOutput;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;

public interface UserTypeService extends Service {

	UserTypeServiceOutput getUserTypeById(Integer id) throws DataNotFoundException;

	UserTypeServiceOutput getUserTypeByName(String username);

	UserTypeServiceOutput createUserType(
			UserTypeServiceInput userServiceInput) throws DataAlreadyExistException;

	UserTypeServiceOutput updateUserType(Integer id,
			UserTypeServiceInput userServiceInput) throws DataNotFoundException;

	UserTypeServiceOutput deleteUserType(Integer id) throws DataNotFoundException;

	List<UserTypeServiceOutput> listAll();

}
