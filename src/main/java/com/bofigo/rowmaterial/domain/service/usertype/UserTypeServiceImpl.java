package com.bofigo.rowmaterial.domain.service.usertype;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bofigo.rowmaterial.constant.ApplicationConstants;
import com.bofigo.rowmaterial.dao.model.UserTypeModel;
import com.bofigo.rowmaterial.dao.repository.UserTypeRepository;
import com.bofigo.rowmaterial.domain.dto.input.UserTypeServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.UserTypeServiceOutput;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.UserTypeMapper;

@Service
public class UserTypeServiceImpl implements UserTypeService {

	private UserTypeMapper userTypeMapper;
	private UserTypeRepository userTypeRepository;

	public UserTypeServiceImpl(UserTypeRepository userTypeRepository, UserTypeMapper userTypeMapper) {
		this.userTypeRepository = userTypeRepository;
		this.userTypeMapper = userTypeMapper;
	}

	@Override
	public UserTypeServiceOutput getUserTypeById(Integer id) throws DataNotFoundException {
		UserTypeModel userTypeModel = getUserTypeModel(id);
		return prepareUserTypeServiceOutput(userTypeModel);
	}

	@Override
	public UserTypeServiceOutput getUserTypeByName(String name) {
		UserTypeModel userTypeModel = userTypeRepository.findByName(name);
		return prepareUserTypeServiceOutput(userTypeModel);
	}

	@Override
	public UserTypeServiceOutput createUserType(UserTypeServiceInput userTypeServiceInput)
			throws DataAlreadyExistException {
		UserTypeModel userTypeModel = userTypeRepository.findByName(userTypeServiceInput.getName());

		if (userTypeModel != null) {
			throw new DataAlreadyExistException("zaten var");
		}

		UserTypeModel insertedUserTypeModel = insertUserTypeModel(userTypeServiceInput);

		return prepareUserTypeServiceOutput(insertedUserTypeModel);
	}

	@Override
	public UserTypeServiceOutput updateUserType(Integer id, UserTypeServiceInput userTypeServiceInput)
			throws DataNotFoundException {
		UserTypeModel userTypeModel = userTypeRepository.findByName(userTypeServiceInput.getName());

		if (userTypeModel == null) {
			throw new DataNotFoundException("bulunamadı");
		}

		UserTypeModel updatedUserTypeModel = updateUserTypeModel(userTypeModel, userTypeServiceInput);

		return prepareUserTypeServiceOutput(updatedUserTypeModel);
	}

	@Override
	public UserTypeServiceOutput deleteUserType(Integer id) throws DataNotFoundException {
		UserTypeModel userTypeModel = getUserTypeModel(id);
		userTypeRepository.deleteById(id);
		return userTypeMapper.mapModelToServiceOutput(userTypeModel);
	}

	@Override
	public List<UserTypeServiceOutput> listAll() {
		List<UserTypeModel> userTypeModelList = userTypeRepository.findAll();
		return userTypeMapper.mapModelToServiceOutputList(userTypeModelList);
	}

	private UserTypeModel getUserTypeModel(Integer id) throws DataNotFoundException {
		Optional<UserTypeModel> userType = userTypeRepository.findById(id);

		if (!userType.isPresent()) {
			throw new DataNotFoundException("bulunamadı");
		}
		return userType.get();
	}

	public UserTypeServiceOutput prepareUserTypeServiceOutput(UserTypeModel userTypeModel) {
		UserTypeServiceOutput userTypeServiceOutput = new UserTypeServiceOutput();

		if (userTypeModel == null) {
			return userTypeServiceOutput;
		}

		userTypeServiceOutput = userTypeMapper.mapModelToServiceOutput(userTypeModel);

		return userTypeServiceOutput;
	}

	public UserTypeModel insertUserTypeModel(UserTypeServiceInput userTypeServiceInput) {
		UserTypeModel userTypeModel = userTypeMapper.mapServiceInputToModel(userTypeServiceInput);
		userTypeModel.setStatus(ApplicationConstants.ACTIVE);

		return userTypeRepository.save(userTypeModel);
	}

	public UserTypeModel updateUserTypeModel(UserTypeModel userTypeModel, UserTypeServiceInput userTypeServiceInput) {
		userTypeModel = userTypeMapper.mapServiceInputToModel(userTypeServiceInput);

		return userTypeRepository.save(userTypeModel);
	}

}
