package com.ruanko.music.utils;

/**
 * 自定义异常类
 * @author Zuo
 */
public class AppException extends Exception{

	private int exceptionCode; //异常编号
	private String message;   //异常消息
	
	public AppException(String message_){
		this.message = message_;
	}
	
	public AppException(String message_, int exceptionCode_){
		this.exceptionCode = exceptionCode_;
		this.message = message_;
	}

	public int getExceptionCode() {
		return exceptionCode;
	}
	
	public String getMessage() {
		return message;
	}

	public String getDetailMessage() {
		String detailMessage = "Detail message: exception" + this.exceptionCode + " " + message;
		return detailMessage;
	}
	
}