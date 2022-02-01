
package com.melchi.external.common;

import com.melchi.external.common.BaseConst.Code;
import com.melchi.external.common.model.BleApiException;

public interface BaseConst {
	
	public class Code {
		// From ApiConstants
		//공통영역처리
		public final static int SUCCESS = 200;
		public final static String API_SUCCESS_MSG = "정상처리되었습니다.";
		 
		public final static int CREATED = 201;
		public final static String API_CREATED_MSG = "등록되었습니다.";
		
		public final static int DUPLICATE = 202;
		public final static String DUPLICATE_MSG = "중복된 항목이 이미 존재합니다.";
		
		public final static int SELECT_IS_NULL = 203;
		public final static String SELECT_IS_NULL_MSG = "조회내역이 없습니다.";
		
		//업무영역처리
		public final static int SHIPNO_VAL = 300;
		public final static String SHIPNO_VAL_MSG = "배송지 번호가 유효하지 않습니다.";
		
		public final static int CATEGORY_VAL = 301;
		public final static String CATEGORY_VAL_MSG = "카테고리 번호가 유효하지 않습니다.";
		
		public final static int DUPLICATE_OPTION_VAL = 303;
		public final static String DUPLICATE_OPTION_VAL_MSG = "카테고리 번호가 유효하지 않습니다.";

		public final static int REGI_OPT_VAL = 304;
		public final static String REGI_OPT_VAL_MSG = "등록된 옵션이 있습니다.";
		
		public final static int STATUS_VAL = 305;
		public final static String STATUS_VAL_MSG = "주문상태값이 잘못들어왔습니다";
		
		public final static int EXCHANGE_CONFIRM_VAL = 306;
		public final static String EXCHANGE_CONFIRM_VAL_MSG = "해당 주문건은 교환철회 되었습니다.";

		public final static int REFUUND_CONFIRM_VAL = 307;
		public final static String REFUUND_CONFIRM_VAL_MSG = "해당 주문건은 반품철회 되었습니다.";
		
		
		public final static int INVALID_ERROR = 400;
		public final static String INVALID_ERROR_MSG = "필수값이 누락되었습니다.";

		public final static int INVALID_TYPE_ERROR = 401;
		public final static String INVALID_TYPE_ERROR_MSG = "권한이없습니다.";
		
		public final static int FORBIDDEN = 403;
		public final static String FORBIDDEN_MSG = "금지된상태입니다.";
		
		public final static int NOT_FOUND = 404;
		public final static String NOT_FOUND_MSG = "찾을수없습니다.";
		
		public final static int AUTH_TOKEN_EXPIRED = 408;
		public final static String AUTH_TOKEN_EXPIRED_MSG = "인증이 해제되었습니다.";
		
		public final static int SYSTEM_ERROR = 900;		
		public final static String SYSTEM_ERROR_MSG = "시스템이 종료되었습니다.";

	}
	
}
