package com.pas.rastatask.retrouser;

import com.google.gson.annotations.SerializedName;

public class MessageItem{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	@SerializedName("username")
	private String username;

	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}

	public String getUsername(){
		return username;
	}
}