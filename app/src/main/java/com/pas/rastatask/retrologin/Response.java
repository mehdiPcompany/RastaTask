package com.pas.rastatask.retrologin;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("code")
	private int code;

    @SerializedName("message")
    private Message message;

	public int getCode(){
		return code;
	}


	public Message getMessage(){
		return message;
	}
}