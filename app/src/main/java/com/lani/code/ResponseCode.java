package com.lani.code;

import lombok.Getter;

@Getter
public enum ResponseCode {

	
	C200(200,"success"),
	C900(900, "request parameter Check");
	
	private int code;
	
	private String msg;
	
	
	
	private ResponseCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	
}
