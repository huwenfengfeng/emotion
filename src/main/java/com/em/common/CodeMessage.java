package com.em.common;

import java.io.Serializable;

public class CodeMessage implements Serializable{
	
	private Integer code;
	private String message;
	
	private CodeMessage(){
		
	}
	
    private CodeMessage(Integer code , String message){
		this.code = code;
		this.message = message;
	}
	
	public static CodeMessage LOGIN_SUCCESS = new CodeMessage(50001 , "登录成功");
	public static CodeMessage LOGIN_FAIL = new CodeMessage(50002 , "登录失败");
	
	public Integer getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	
}
