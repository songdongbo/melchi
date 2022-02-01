package com.melchi.external.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BleApiException extends Exception {

	private static final long serialVersionUID = 8451002067678880278L;
	
	private int httpCode;
	private int errorCode;
	private String errorMessage;
	
	
	public BleApiException(int httpCode , int errorCode , String errorMessage){
		this.httpCode = httpCode;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public BleApiException(int errorCode , String errorMessage){
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	@Override
	public String getMessage() {
		if(super.getMessage() != null)
		{
			return errorMessage + " " + super.getMessage();
		}
		return errorMessage;
	}
}
