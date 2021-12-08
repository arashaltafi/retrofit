package com.arash.altafi.retrofit.java.data;

import com.google.gson.annotations.SerializedName;

public class ResponseItem {

	@SerializedName("namefamily")
	private String namefamily;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("id")
	private String id;

	@SerializedName("email")
	private String email;

	public String getNamefamily(){
		return namefamily;
	}

	public String getMobile(){
		return mobile;
	}

	public String getId(){
		return id;
	}

	public String getEmail(){
		return email;
	}
}