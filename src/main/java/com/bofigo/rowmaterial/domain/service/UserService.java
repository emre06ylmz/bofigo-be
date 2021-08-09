package com.bofigo.rowmaterial.domain.service;

import java.util.List;

import com.bofigo.rowmaterial.domain.dto.UserServiceInput;
import com.bofigo.rowmaterial.domain.dto.UserServiceOutput;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;

public interface UserService extends Service {

	UserServiceOutput getUserById(Integer id) throws DataNotFoundException;

	UserServiceOutput getUserByUsername(String username);

	UserServiceOutput createUser(
			UserServiceInput userServiceInput) throws DataAlreadyExistException;

	UserServiceOutput updateUser(Integer id,
			UserServiceInput userServiceInput) throws DataNotFoundException;

	UserServiceOutput deleteUser(Integer id) throws DataNotFoundException;

	List<UserServiceOutput> listAll();

}
