package com.pas.rastatask.retroonetask;

import com.google.gson.annotations.SerializedName;

public class MessageItem{

	@SerializedName("msg")
	private String msg;

	@SerializedName("date")
	private String date;

	@SerializedName("commentmanager")
	private String commentmanager;

	@SerializedName("commentuser")
	private String commentuser;

	@SerializedName("id")
	private String id;

	@SerializedName("colorstatus")
	private String colorstatus;

	@SerializedName("state")
	private String state;

	@SerializedName("title")
	private String title;

	@SerializedName("user")
	private String user;

	@SerializedName("status")
	private String status;

	public String getMsg(){
		return msg;
	}

	public String getDate(){
		return date;
	}

	public String getCommentmanager(){
		return commentmanager;
	}

	public String getCommentuser(){
		return commentuser;
	}

	public String getId(){
		return id;
	}

	public String getColorstatus(){
		return colorstatus;
	}

	public String getState(){
		return state;
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
}