package com.bofigo.rowmaterial.domain.service.usertype;

import java.util.List;

import com.bofigo.rowmaterial.domain.dto.input.UserTypeServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.UserTypeServiceOutput;
import com.bofigo.rowmaterial.domain.service.Service;
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
