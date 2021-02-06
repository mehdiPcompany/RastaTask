package com.pas.rastatask.retroaddtask;

import com.google.gson.annotations.SerializedName;

public class AddTask{

	@SerializedName("response")
	private Response response;

	public Response getResponse(){
		return response;
	}
}