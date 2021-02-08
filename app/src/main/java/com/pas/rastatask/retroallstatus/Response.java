package com.pas.rastatask.retroallstatus;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response{

	@SerializedName("code")
	private int code;

	@SerializedName("message")
	private List<MessageItem> message;

	public int getCode(){
		return code;
	}

	public List<MessageItem> getMessage(){
		return message;
	}
}