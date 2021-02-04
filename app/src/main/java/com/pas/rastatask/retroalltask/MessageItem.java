package com.pas.rastatask.retroalltask;

import com.google.gson.annotations.SerializedName;

public class MessageItem{

	@SerializedName("date")
	private String date;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	@SerializedName("user")
	private String user;

	@SerializedName("status")
	private String status;

	@SerializedName("comment")
	private String comment;

	@SerializedName("colorstatus")
	private String colorstatus;

	public String getDate(){
		return date;
	}

	public String getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getUser(){
		return user;
	}

	public String getStatus(){
		return status;
	}

	public String getComment(){
		return comment;
	}

	public String getColorStatus(){
		return colorstatus;
	}

}