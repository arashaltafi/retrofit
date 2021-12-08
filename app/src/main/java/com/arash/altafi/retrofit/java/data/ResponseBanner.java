package com.arash.altafi.retrofit.java.data;

import com.google.gson.annotations.SerializedName;

public class ResponseBanner{

	@SerializedName("image")
	private String image;

	@SerializedName("id")
	private int id;

	public String getImage(){
		return image;
	}

	public int getId(){
		return id;
	}
}