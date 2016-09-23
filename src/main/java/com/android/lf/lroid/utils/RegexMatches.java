package com.android.lf.lroid.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches {

	public static boolean matchesPhone(String phone) {
		String pattern = "(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7}";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(phone);
		return m.matches();
	}

}