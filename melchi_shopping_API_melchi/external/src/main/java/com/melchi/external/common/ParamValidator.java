package com.melchi.external.common;

import java.util.Map;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.melchi.external.common.ResponseCode;

public class ParamValidator {

	private boolean accurate; // 검증결과
	private String errorCode; // 에러 코드
	private String errorMessage; // 에러 메세지
	private String fieldName;
	
	public ParamValidator() {
		this.accurate = true;
		this.errorCode = "";
		this.errorMessage = "";
		this.fieldName = "";
	}

	public void nullChk(Object obj, String[] arrCheckParam) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = mapper.convertValue(obj, Map.class);
		for (int i = 0; i < arrCheckParam.length; i++) {
			if (StringUtils.isEmpty(map.get(arrCheckParam[i]))) {
				
				if(StringUtils.isEmpty(this.fieldName)) {
					this.fieldName = arrCheckParam[i];
				} else {
					this.fieldName += ","+arrCheckParam[i];
				}
				
				this.accurate = false;
				this.errorCode = ResponseCode.INVALID_PARAM.getRetCode();
				this.errorMessage = ResponseCode.INVALID_PARAM.getRetSysMsg() + " [" + this.fieldName + "]";
			}
		}
	}

	public void nullChk(String jsonStr, String[] arrCheckParam) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = mapper.readValue(jsonStr, new TypeReference<Map<String, String>>() {});
		for (int i = 0; i < arrCheckParam.length; i++) {
			if (StringUtils.isEmpty(map.get(arrCheckParam[i]))) {
				
				if(StringUtils.isEmpty(this.fieldName)) {
					this.fieldName = arrCheckParam[i];
				} else {
					this.fieldName += ","+arrCheckParam[i];
				}
				
				this.accurate = false;
				this.errorCode = ResponseCode.INVALID_PARAM.getRetCode();
				this.errorMessage = ResponseCode.INVALID_PARAM.getRetSysMsg() + " [" + this.fieldName + "]";
			}
		}
	}

	/**
	 * 파라미터 null 체크 - null 체크하여 null 이면 해당 메시지를 담는다
	 * 
	 * @param key
	 * @param value
	 */
	public void nullChk(String key, String value) {
		if (StringUtils.isEmpty(value)) {
			
			if(!StringUtils.isEmpty(key)) {
				if(StringUtils.isEmpty(this.fieldName)) {
					this.fieldName = key;
				} else {
					this.fieldName += ","+key;
				}
			}
			String addMessage = StringUtils.isEmpty(this.fieldName) ? "" : " [" + this.fieldName + "]";
			
			this.accurate = false;
			this.errorCode = ResponseCode.INVALID_PARAM.getRetCode();
			this.errorMessage = ResponseCode.INVALID_PARAM.getRetSysMsg() + addMessage;
		}
	}

	public void extnalNullChk(String key, String value) {
		if (StringUtils.isEmpty(value)) {
			
			if(!StringUtils.isEmpty(key)) {
				if(StringUtils.isEmpty(this.fieldName)) {
					this.fieldName = key;
				} else {
					this.fieldName += ","+key;
				}
			}
			String addMessage = StringUtils.isEmpty(this.fieldName) ? "" : " [" + this.fieldName + "]";
			
			this.accurate = false;
			this.errorCode = ResponseCode.EXTNAL_INVALID_PARAM.getRetCode();
			this.errorMessage = ResponseCode.EXTNAL_INVALID_PARAM.getRetSysMsg() + addMessage;
		}
	}
	
	public void nullChk(Object obj) {
		if (StringUtils.isEmpty(obj)) {
			this.accurate = false;
			this.errorCode = ResponseCode.INVALID_PARAM.getRetCode();
			this.errorMessage = ResponseCode.INVALID_PARAM.getRetSysMsg();
		}
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public boolean isAccurate() {
		return accurate;
	}

	public void setAccurate(boolean accurate) {
		this.accurate = accurate;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
