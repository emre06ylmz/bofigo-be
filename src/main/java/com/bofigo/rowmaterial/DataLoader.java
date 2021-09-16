package com.bofigo.rowmaterial;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.bofigo.rowmaterial.constant.ApplicationConstants;
import com.bofigo.rowmaterial.dao.model.CurrencySettingsModel;
import com.bofigo.rowmaterial.dao.model.RawMaterialCategoryModel;
import com.bofigo.rowmaterial.dao.model.UserModel;
import com.bofigo.rowmaterial.dao.model.UserTypeModel;
import com.bofigo.rowmaterial.dao.repository.CurrencySettingsRepository;
import com.bofigo.rowmaterial.dao.repository.RawMaterialCategoryRepository;
import com.bofigo.rowmaterial.dao.repository.UserRepository;
import com.bofigo.rowmaterial.dao.repository.UserTypeRepository;

@Component
public class DataLoader implements ApplicationRunner {

	private RawMaterialCategoryRepository rawMaterialCategoryRepository;
	private UserRepository userRepository;
	private UserTypeRepository userTypeRepository;
	private CurrencySettingsRepository currencySettingsRepository;

	@Autowired
	public DataLoader(RawMaterialCategoryRepository rawMaterialCategoryRepository, UserRepository userRepository,
			UserTypeRepository userTypeRepository, CurrencySettingsRepository currencySettingsRepository) {
		this.rawMaterialCategoryRepository = rawMaterialCategoryRepository;
		this.userRepository = userRepository;
		this.userTypeRepository = userTypeRepository;
		this.currencySettingsRepository = currencySettingsRepository;
	}

	public void run(ApplicationArguments args) {
		insertUsers();
		insertUserTypes();
		insertRawMaterialCategories();
		insertCurrencySetting();
	}

	private void insertUsers() {
		UserModel user1 = new UserModel();
		user1.setUsername("eneshelvaci@bofigo.com");
		user1.setName("enes");
		user1.setSurname("helvaci");
		user1.setPassword("12345");
		user1.setRole(ApplicationConstants.ROLE_ADMIN);

		UserModel user2 = new UserModel();
		user2.setUsername("emresonmez@bofigo.com");
		user2.setName("emre");
		user2.setSurname("sonmez");
		user2.setPassword("12345");
		user2.setRole(ApplicationConstants.ROLE_STANDARD);

		UserModel user3 = new UserModel();
		user3.setUsername("senemguvener@bofigo.com");
		user3.setName("senem");
		user3.setSurname("guvener");
		user3.setPassword("12345");
		user3.setRole(ApplicationConstants.ROLE_STANDARD);

		UserModel user4 = new UserModel();
		user4.setUsername("batuhankacmaz@bofigo.com");
		user4.setName("batuhan");
		user4.setSurname("kacmaz");
		user4.setPassword("12345");
		user4.setRole(ApplicationConstants.ROLE_STANDARD);

		UserModel user5 = new UserModel();
		user5.setUsername("omertalas@bofigo.com");
		user5.setName("omer");
		user5.setSurname("talas");
		user5.setPassword("12345");
		user5.setRole(ApplicationConstants.ROLE_STANDARD);

		List<UserModel> list = userRepository.findAll();
		if (list.size() < 1) {
			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(user3);
			userRepository.save(user4);
			userRepository.save(user5);
		}

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

		List<UserTypeModel> list = userTypeRepository.findAll();
		if (list.size() < 1) {
			userTypeRepository.save(userType1);
			userTypeRepository.save(userType2);
			userTypeRepository.save(userType3);
		}

	}

	private void insertRawMaterialCategories() {
		RawMaterialCategoryModel category1 = new RawMaterialCategoryModel();
		category1.setName("Plastik");
		category1.setExplanation("Plastik");

		RawMaterialCategoryModel category2 = new RawMaterialCategoryModel();
		category2.setName("Demir");
		category2.setExplanation("Demir");

		List<RawMaterialCategoryModel> list = rawMaterialCategoryRepository.findAll();
		if (list.size() < 1) {
			rawMaterialCategoryRepository.save(category1);
			rawMaterialCategoryRepository.save(category2);
		}

	}

	private void insertCurrencySetting() {
		CurrencySettingsModel model = new CurrencySettingsModel();
		model.setDollar(4);
		model.setEuro(5);
		model.setLastUpdateDate(new Date());

		List<CurrencySettingsModel> list = currencySettingsRepository.findAll();
		if (list.size() < 1) {
			currencySettingsRepository.save(model);
		}
	}

}
