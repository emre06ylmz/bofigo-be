package com.bofigo.rowmaterial.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StringUtilTest {

	@Test
	public void test_StringUtil() {
		String str1 = "abc";
		String str2 = null;

		assertTrue(StringUtil.isNullOrEmpty(str2));
		assertFalse(StringUtil.isNullOrEmpty(str1));
	}

}
