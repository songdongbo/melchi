package com.melchi.external.common;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.melchi.external.common.ResponseCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseTemplate {
    private String resultCode;
    private String resultMsg;
    private Object data;


    public ResponseTemplate() {
        this.resultCode = ResponseCode.SUCCESS.getRetCode();
        this.resultMsg = ResponseCode.SUCCESS.getRetSysMsg();
    }
    public ResponseTemplate(Object data) {
        this.resultCode = ResponseCode.SUCCESS.getRetCode();
        this.resultMsg = ResponseCode.SUCCESS.getRetSysMsg();
        this.data = data;
    }
    public ResponseTemplate(String code, String msg) {
        this.resultCode = code;
        this.resultMsg = msg;
    }

    public ResponseTemplate(ResponseCode responseCode) {
        this.resultCode = responseCode.getRetCode();
        this.resultMsg = responseCode.getRetSysMsg();

        if(StringUtils.isEmpty(responseCode.getRetMsg())) {
            this.resultMsg = responseCode.getRetSysMsg();
        }else if(responseCode.getRetMsg().equals("fail")) {
            this.resultMsg = ResponseCode.FAIL.getRetMsg();
        }else {
            this.resultMsg = responseCode.getRetMsg();
        }
    }

    public ResponseTemplate(String code, String msg, Object data) {
        this.resultCode = code;
        this.resultMsg = msg;
        this.data = data;
    }
    
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
    
    
}
