package com.pas.rastatask.retroallstate;

import com.google.gson.annotations.SerializedName;

public class MessageItem{

	@SerializedName("id")
	private String id;

	@SerializedName("text")
	private String text;

	public String getId(){
		return id;
	}

	public String getText(){
		return text;
	}
}