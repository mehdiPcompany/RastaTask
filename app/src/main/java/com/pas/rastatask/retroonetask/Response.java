package com.pas.rastatask.retroonetask;

import java.util.List;
import com.google.gson.annotations.SerializedName;

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