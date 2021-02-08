package com.pas.rastatask.retrouser;

import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("response")
	private Response response;

	public Response getResponse(){
		return response;
	}
}