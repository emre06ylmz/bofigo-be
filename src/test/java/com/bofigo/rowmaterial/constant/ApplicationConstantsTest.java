package com.bofigo.rowmaterial.constant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ApplicationConstantsTest {

	public static final String ACTIVE = "ACTIVE";
	public static final String PASSIVE = "PASSIVE";
	
	public static final String ROUTE_AUTHENTICATION = "/api/application";
	public static final String ROUTE_LOGIN = "/login";
	
	@Test
	public void test_constants() {
		assertEquals(ACTIVE, ApplicationConstants.ACTIVE);
		assertEquals(PASSIVE, ApplicationConstants.PASSIVE);
		assertEquals(ROUTE_AUTHENTICATION, ApplicationConstants.ROUTE_AUTHENTICATION);
		assertEquals(ROUTE_LOGIN, ApplicationConstants.ROUTE_LOGIN);
	}
	
}
