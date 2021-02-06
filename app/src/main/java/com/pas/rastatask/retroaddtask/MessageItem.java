package com.pas.rastatask.retroaddtask;

import com.google.gson.annotations.SerializedName;

public class MessageItem{

	@SerializedName("msg")
	private String msg;

	public String getMsg(){
		return msg;
	}
}