package com.library.util;

public class StringUtil {

	/**
	 * ÅÐ¶Ï×Ö·û´®ÊÇ·ñÎªnull " "
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if (str == null || str.trim().length() == 0) {
			return true;
		}
		return false;
	}
	
}
