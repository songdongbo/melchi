package com.melchi.external.common.model.response;

import java.io.Serializable;

import com.melchi.external.common.model.BleApiException;

import lombok.Data;

@Data
public class ResponseHeader implements Payload, Serializable {

	private static final long serialVersionUID = 8009272581530140412L;
	
	private Header header;
	
	public ResponseHeader(){
		this.header = new Header();

	}

	public ResponseHeader(BleApiException e) {
		this.header = new Header();
		header.setStatusCode(e.getErrorCode());
		header.setStatusMessage(e.getErrorMessage());
	} 
}
