package com.pas.rastatask.retroallstatus;

import com.google.gson.annotations.SerializedName;

public class Status {

	@SerializedName("response")
	private Response response;

	public Response getResponse(){
		return response;
	}
}