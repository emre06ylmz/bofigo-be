package com.bofigo.rowmaterial.dao.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;

public class RawMaterialCategoryModelTest {

	@Test
	public void test_objNull()
			throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		RawMaterialCategoryModel model = new RawMaterialCategoryModel();
		assertNotNull(model);

		for (PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(RawMaterialCategoryModel.class)
				.getPropertyDescriptors()) {
			if (propertyDescriptor.getReadMethod() != null && !"class".equals(propertyDescriptor.getName())) {
				assertNull(propertyDescriptor.getReadMethod().invoke(model, null));
			}
		}
	}

	@Test
	public void test_objNotNull()
			throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		RawMaterialCategoryModel model = new RawMaterialCategoryModel();
		assertNotNull(model);

		model.prePersist();
		model.setId(Integer.valueOf(1));
		model.setName("deneme");
		model.setExplanation("deneme");

		for (PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(RawMaterialCategoryModel.class)
				.getPropertyDescriptors()) {
			if (propertyDescriptor.getReadMethod() != null && !"class".equals(propertyDescriptor.getName())) {
				assertNotNull(propertyDescriptor.getReadMethod().invoke(model, null));
			}
		}
	}

}
