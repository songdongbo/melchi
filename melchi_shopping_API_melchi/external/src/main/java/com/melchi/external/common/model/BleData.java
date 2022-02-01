package com.melchi.external.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class BleData extends NameValuePair {

	String ownerId;
	
	char publicYn = 'Y';
	char useYn = 'Y';
	
	String createdDate;
	String modifiedDate;
	
	public BleData() {}
	
	public BleData(String name, String value) {
		super(name, value);
	}
}
