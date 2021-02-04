package com.pas.rastatask.retroalltask;

import com.google.gson.annotations.SerializedName;

public class Task{

	@SerializedName("response")
	private Response response;

	public Response getResponse(){
		return response;
	}
}