package com.melchi.external.common.model;

import lombok.Data;

@Data
public class NameValuePair {

	protected String name;
	protected String value;
	
	public NameValuePair() {}
	
	public NameValuePair(String name, String value) {
		this.name = name;
		this.value = value;
	}
}
