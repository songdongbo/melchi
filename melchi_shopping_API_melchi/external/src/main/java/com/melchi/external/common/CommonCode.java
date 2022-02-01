package com.melchi.external.common;

public interface CommonCode {
	
	public final static String CLUSTER_STATUS_USE = "CLUSTER_STATUS_USE";	//가맹점 사용 상태 - 사용중
	public final static String CLUSTER_STATUS_PAUSE = "CLUSTER_STATUS_PAUSE";	//가맹점 사용 상태 - 일시중지
	public final static String CLUSTER_STATUS_EXPIRE = "CLUSTER_STATUS_EXPIRE";	//가맹점 사용 상태 - 종료
	
	public final static String DEVICE_STATUS_STOCK = "DEVICE_STATUS_STOCK";	//디바이스 입고 상태
	public final static String DEVICE_STATUS_INSTALL_COMPLETE = "DEVICE_STATUS_INSTALL_COMPLETE";	//디바이스 설치 완료 상태
	
	public final static String OUR_COMPANY = "OUR_COMPANY";			//자사 코드
	public final static String OTHER_COMPANY = "OTHER_COMPANY";			//타사 코드
	
	public final static String OUR_DEVICE_TYPE_AP = "OUR_DEVICE_TYPE_AP";			//AP 코드
	public final static String OUR_DEVICE_TYPE_BC = "OUR_DEVICE_TYPE_BC";		//일반 비콘 코드
	public final static String OUR_DEVICE_TYPE_SB = "OUR_DEVICE_TYPE_SB";	//스마트 비콘 코드
	
	public final static String OUR_DEVICE_TYPE_AP_NAME = "AP";			//AP 코드명
	public final static String OUR_DEVICE_TYPE_BC_NAME = "Beacon";		//일반 비콘 코드명
	public final static String OUR_DEVICE_TYPE_SB_NAME = "Smart Beacon";	//스마트 비콘 코드명
	public final static String OUR_DEVICE_TYPE_XLIFE_NAME = "Xlife";		//일반 비콘 XLife용 코드명

	public final static String UUID = "0288DCBAC7B2436CBED44E9256E67F89";	//UUID
	
	public final static String INSTALL_REQUEST	=	"INSTALL_REQUEST";	//설치 상태(요청)
	
	public final String TYPE_BC = "BC";	//BLE Beacon
	public final String TYPE_SB = "SB";	//BLE Smart Beacon
	public final String TYPE_AP = "AP";	//WIFI AP
	
	public final String WIFI = "WIFI";	//WIFI AP
	public final String BLE = "BLE";	//WIFI AP
	
	public final static String SCAN_SPEED = "scanSpeed";
	public final static String NORMAL = "normal";
	
	public final static String CAMPAIGN_STATUS_USE = "USE";			//캠페인 상태 사용
	public final static String CAMPAIGN_STATUS_PAUSE = "PAUSE";			//캠페인 상태 일시중지
	public final static String CAMPAIGN_STATUS_END = "END";			//캠페인 상태 종료
	public final static String CAMPAIGN_STATUS_WAIT = "WAIT";			//사용대기
	
	public final static String GUARD_TIME	=	"guardTime";		//가드타임
	
}
