/*
 * Created by Storm Zhang, Feb 13, 2014.
 */

package com.android.lf.lroid.volley;

import android.util.Log;

import java.util.HashMap;

public class ApiParams extends HashMap<String, String> {
	private static final long serialVersionUID = 8112047472727256876L;

	public ApiParams with(String key, String value) {
		put(key, value);
		return this;
	}


	@Override
	public String toString() {
		Log.e("TAG","change string is --------->  "+super.toString().replace("{","").replace("}",""));
		return super.toString().replace("{","").replace("}","");
	}
}
