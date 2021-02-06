package com.pas.rastatask.retroallstate;

import com.google.gson.annotations.SerializedName;

public class State{

	@SerializedName("response")
	private Response response;

	public Response getResponse(){
		return response;
	}
}