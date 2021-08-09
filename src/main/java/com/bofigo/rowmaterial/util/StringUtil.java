package com.bofigo.rowmaterial.util;

public class StringUtil {

	public static boolean isNullOrEmpty(String str) {
		return str == null || "".equals(str) || "".equals(str.trim());
	}

}
