package com.arash.altafi.retrofit.java.data;

import com.google.gson.annotations.SerializedName;

public class ResponseMessage{

	@SerializedName("message")
	private String message;

	public String getMessage(){
		return message;
	}
}