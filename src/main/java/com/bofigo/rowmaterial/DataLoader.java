package com.bofigo.rowmaterial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.bofigo.rowmaterial.constant.ApplicationConstants;
import com.bofigo.rowmaterial.dao.model.RawMaterialCategoryModel;
import com.bofigo.rowmaterial.dao.model.UserModel;
import com.bofigo.rowmaterial.dao.model.UserTypeModel;
import com.bofigo.rowmaterial.dao.repository.RawMaterialCategoryRepository;
import com.bofigo.rowmaterial.dao.repository.UserRepository;
import com.bofigo.rowmaterial.dao.repository.UserTypeRepository;

@Component
public class DataLoader implements ApplicationRunner {

	private RawMaterialCategoryRepository rawMaterialCategoryRepository;
	private UserRepository userRepository;
	private UserTypeRepository userTypeRepository;

	@Autowired
	public DataLoader(RawMaterialCategoryRepository rawMaterialCategoryRepository, UserRepository userRepository,
			UserTypeRepository userTypeRepository) {
		this.rawMaterialCategoryRepository = rawMaterialCategoryRepository;
		this.userRepository = userRepository;
		this.userTypeRepository = userTypeRepository;
	}

	public void run(ApplicationArguments args) {
		insertUsers();
		insertUserTypes();
		insertRawMaterialCategories();
	}

	private void insertUsers() {
		UserModel user1 = new UserModel();
		user1.setUsername("h@gmail.com");
		user1.setName("Halil");
		user1.setSurname("Dany");
		user1.setPassword("a");
		user1.setRole(ApplicationConstants.ROLE_DEVELOPER);

		UserModel user2 = new UserModel();
		user2.setUsername("m@gmail.com");
		user2.setName("Mahmut");
		user2.setSurname("Alkan");
		user2.setPassword("a");
		user2.setRole(ApplicationConstants.ROLE_STANDARD);

		UserModel user3 = new UserModel();
		user3.setUsername("k@gmail.com");
		user3.setName("Kamil");
		user3.setSurname("Zenci");
		user3.setPassword("a");
		user3.setRole(ApplicationConstants.ROLE_ADMIN);

		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
	}

	private void insertUserTypes() {
		UserTypeModel userType1 = new UserTypeModel();
		userType1.setName(ApplicationConstants.ROLE_DEVELOPER);
		userType1.setDetail("DEVELOPER");

		UserTypeModel userType2 = new UserTypeModel();
		userType2.setName(ApplicationConstants.ROLE_STANDARD);
		userType2.setDetail("STANDARD");

		UserTypeModel userType3 = new UserTypeModel();
		userType3.setName(ApplicationConstants.ROLE_ADMIN);
		userType3.setDetail("ADMIN");
		
		userTypeRepository.save(userType1);
		userTypeRepository.save(userType2);
		userTypeRepository.save(userType3);
	}

	private void insertRawMaterialCategories() {
		RawMaterialCategoryModel category1 = new RawMaterialCategoryModel();
		category1.setName("Plastik");
		category1.setExplanation("Plastik");

		RawMaterialCategoryModel category2 = new RawMaterialCategoryModel();
		category2.setName("Demir");
		category2.setExplanation("Demir");

		rawMaterialCategoryRepository.save(category1);
		rawMaterialCategoryRepository.save(category2);
	}
}
