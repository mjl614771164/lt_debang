package com.leting.util;

public class MyException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private String code;
	private String mess;
	private Throwable e;
	
	
	
	public MyException(String code, String mess) {
		super();
		this.code = code;
		this.mess = mess;
	}
	
	
	public MyException(String code, String mess, Throwable e) {
		super();
		this.code = code;
		this.mess = mess;
		this.e = e;
	}


	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMess() {
		return mess;
	}
	public void setMess(String mess) {
		this.mess = mess;
	}
	public Throwable getE() {
		return e;
	}
	public void setE(Throwable e) {
		this.e = e;
	}
	

}
