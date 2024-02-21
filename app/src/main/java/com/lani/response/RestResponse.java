package com.lani.response;

import com.lani.code.ResponseCode;

import lombok.Data;

@Data
public class RestResponse {

	
	private boolean success;
	
	private ResponseCode data;
	
	public RestResponse(boolean success, ResponseCode data){
		
		this.success = success;
		this.data = data;

	}
}
