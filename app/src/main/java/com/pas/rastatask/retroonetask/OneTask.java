package com.pas.rastatask.retroonetask;

import com.google.gson.annotations.SerializedName;

public class OneTask{

	@SerializedName("response")
	private Response response;

	public Response getResponse(){
		return response;
	}
}