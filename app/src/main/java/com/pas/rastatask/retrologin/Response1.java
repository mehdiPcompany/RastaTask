package com.pas.rastatask.retrologin;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response1{

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private List<Message> message = null;

    public int getCode(){
        return code;
    }


    public List<Message> getMessage() {
        return message;
    }
}