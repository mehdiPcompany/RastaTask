package com.pas.rastatask.retrologin;

import com.google.gson.annotations.SerializedName;

public class Login{

	@SerializedName("response")
	private Response response;

	public Response getResponse(){
		return response;
	}
}