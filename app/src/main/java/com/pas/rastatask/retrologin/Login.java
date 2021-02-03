package com.pas.rastatask.retrologin;

import com.google.gson.annotations.SerializedName;

public class Login{

	@SerializedName("response")
	private Response response;

	@SerializedName("response")
	private Response1 response1;

	public Response getResponse(){
		return response;
	}

	public Response1 getResponse1(){
		return response1;
	}
}