package com.em.common;

import java.io.Serializable;

public class RestResult<T> implements Serializable{
	
	private Integer code;
	private String message;
	private T data;
	
    private RestResult(){
	}
	
	private RestResult(Integer code , String message , T data){
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	private RestResult(CodeMessage codeMessage){
		this.code = codeMessage.getCode();
		this.message = codeMessage.getMessage();
	}
	
	public static <T> RestResult<T> SUCCESS(T data){
		return new RestResult(200 , "返回数据成功" , data);
	}
	
	public static <T> RestResult<T> ERROR(T data){
		return new RestResult(222 , "返回数据失败" , data);
	}
	
	public static <T> RestResult<T> INFO(CodeMessage codeMessage){
		return new RestResult<T>(codeMessage);
	}
	
	public Integer getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	public T getData() {
		return data;
	}
	
	

}
