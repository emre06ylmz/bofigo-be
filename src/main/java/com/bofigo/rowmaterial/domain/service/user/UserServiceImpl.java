package com.bofigo.rowmaterial.domain.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bofigo.rowmaterial.constant.ApplicationConstants;
import com.bofigo.rowmaterial.dao.model.UserModel;
import com.bofigo.rowmaterial.dao.repository.UserRepository;
import com.bofigo.rowmaterial.domain.dto.input.UserServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.UserServiceOutput;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	private UserMapper userMapper;
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Override
	public UserServiceOutput getUserById(Integer id) throws DataNotFoundException {
		UserModel userModel = getUserModel(id);
		return prepareUserServiceOutput(userModel);
	}

	@Override
	public UserServiceOutput getUserByUsername(String username) {
		UserModel userModel = userRepository.findByUsername(username);
		return prepareUserServiceOutput(userModel);
	}

	@Override
	public UserServiceOutput createUser(UserServiceInput userServiceInput) throws DataAlreadyExistException {
		UserModel userModel = userRepository.findByUsername(userServiceInput.getUsername());

		if (userModel != null) {
			throw new DataAlreadyExistException("zaten var");
		}

		UserModel insertedUserModel = insertUserModel(userServiceInput);

		return prepareUserServiceOutput(insertedUserModel);
	}

	@Override
	public UserServiceOutput updateUser(Integer id, UserServiceInput userServiceInput) throws DataNotFoundException {
		UserModel userModel = userRepository.findByUsername(userServiceInput.getUsername());

		if (userModel == null) {
			throw new DataNotFoundException("bulunamadı");
		}

		UserModel updatedUserModel = updateUserModel(userModel, userServiceInput);

		return prepareUserServiceOutput(updatedUserModel);
	}

	@Override
	public UserServiceOutput deleteUser(Integer id) throws DataNotFoundException {
		UserModel userModel = getUserModel(id);
		userRepository.deleteById(id);
		return userMapper.mapModelToServiceOutput(userModel);
	}

	@Override
	public List<UserServiceOutput> listAll() {
		List<UserModel> userModelList = userRepository.findAll();
		return userMapper.mapModelToServiceOutputList(userModelList);
	}

	private UserModel getUserModel(Integer id) throws DataNotFoundException {
		Optional<UserModel> user = userRepository.findById(id);

		if (!user.isPresent()) {
			throw new DataNotFoundException("bulunamadı");
		}
		return user.get();
	}

	public UserServiceOutput prepareUserServiceOutput(UserModel userModel) {
		UserServiceOutput userServiceOutput = new UserServiceOutput();

		if (userModel == null) {
			return userServiceOutput;
		}

		userServiceOutput = userMapper.mapModelToServiceOutput(userModel);

		return userServiceOutput;
	}

	public UserModel insertUserModel(UserServiceInput userServiceInput) {
		UserModel userModel = userMapper.mapServiceInputToModel(userServiceInput);
		userModel.setStatus(ApplicationConstants.ACTIVE);

		return userRepository.save(userModel);
	}

	public UserModel updateUserModel(UserModel userModel, UserServiceInput userServiceInput) {
		int id = userModel.getId();
		userModel = userMapper.mapServiceInputToModel(userServiceInput);
		userModel.setId(id);
		return userRepository.save(userModel);
	}

}
