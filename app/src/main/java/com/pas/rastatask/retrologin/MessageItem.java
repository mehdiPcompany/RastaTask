package com.pas.rastatask.retrologin;

import com.google.gson.annotations.SerializedName;

public class MessageItem{

	@SerializedName("msg")
	private String msg;

	@SerializedName("idUser")
	private String idUser;

	@SerializedName("nameUser")
	private String nameUser;

	@SerializedName("typeUser")
	private String typeUser;

	@SerializedName("username")
	private String username;

	@SerializedName("token")
	private String token;

	public String getMsg(){
		return msg;
	}

	public String getIdUser(){
		return idUser;
	}

	public String getNameUser(){
		return nameUser;
	}

	public String getTypeUser(){
		return typeUser;
	}

	public String getUsername(){
		return username;
	}

	public String getToken(){
		return token;
	}
}