package com.melchi.external.common.model;

import lombok.Data;

/**
 * @author oopdual
 * 결과 코드 및 메시지 필요할 경우 사용
 */
@Data
public class Result {
	
	private String status;	//결과 상태 코드
	private String statusMsg;	//결과 메시지

}
