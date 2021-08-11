package com.bofigo.rowmaterial.dao.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;

import com.bofigo.rowmaterial.constant.ApplicationConstants;

public class UserModelTest {

	@Test
	public void test_objNull()
			throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		UserModel model = new UserModel();
		assertNotNull(model);

		for (PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(UserModel.class)
				.getPropertyDescriptors()) {
			if (propertyDescriptor.getReadMethod() != null && !"class".equals(propertyDescriptor.getName())) {
				assertNull(propertyDescriptor.getReadMethod().invoke(model, null));
			}
		}
	}

	@Test
	public void test_objNotNull()
			throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		UserModel model = new UserModel();
		assertNotNull(model);

		model.prePersist();
		model.setId(Integer.valueOf(1));
		model.setName("deneme");
		model.setPassword("deneme");
		model.setRole(ApplicationConstants.ROLE_DEVELOPER);
		model.setStatus(ApplicationConstants.ACTIVE);
		model.setSurname("deneme");
		model.setUsername("deneme");

		for (PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(UserModel.class)
				.getPropertyDescriptors()) {
			if (propertyDescriptor.getReadMethod() != null && !"class".equals(propertyDescriptor.getName())) {
				assertNotNull(propertyDescriptor.getReadMethod().invoke(model, null));
			}
		}
	}

}
