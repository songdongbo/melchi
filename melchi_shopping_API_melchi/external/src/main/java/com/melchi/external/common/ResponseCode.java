package com.melchi.external.common;

public enum ResponseCode {
    SUCCESS("200", "SUCCESS", "SUCCESS"),
    CREATED("201", "등록되었습니다", "등록되었습니다"),
    UPDATED("202", "수정되었습니다", "수정되었습니다"),
    UNAUTHORIZED("401", "권한이없습니다", "권한이없습니다"),
    FORBIDDEN("403", "금지된상태입니다", "금지된상태입니다"),
    NOT_FOUND("404", "찾을수없습니다", "찾을수없습니다"),
    
    
    FAIL("E00", "예기치 못한 오류가 발생하였습니다.", "예기치 못한 오류가 발생하였습니다."),
    INVALID_PARAM("E01", "요청 파라메터가 잘못 되었습니다.", "요청 파라메터가 잘못 되었습니다"),
    EXTNAL_INVALID_PARAM("E02", "응답 파라메터가 잘못 되었습니다.", "응답 파라메터가 잘못 되었습니다."),
    DATABASE_ERROR("D01", "DB오류", "fail"),
    SELECT_IS_NULL("S01", "조회내역없음", "조회내역없음"),
    EXCHANGE_CONFIRM("E02", "해당 주문건은 교환철회 되었습니다.", "해당 주문건은 교환철회 되었습니다."),
    REFUUND_CONFIRM("E03", "해당 주문건은 반품철회 되었습니다.", "해당 주문건은 반품철회 되었습니다."),
    STATUS_ERROR("E04", "주문상태값이 잘못들어왔습니다.", "주문상태값이 잘못들어왔습니다."),
    
    //타임아웃
    REST_TEMPLATE_TIMEOUT("T99", "API 연동실패", "API 연동실패"),
    ;

    private String retKey;
    private String retCode;
    private String retMsg;
    private String retSysMsg;

    public String getRetKey() {
        return retKey;
    }

    public void setRetKey(String retKey) {
        this.retKey = retKey;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getRetSysMsg() {
        return retSysMsg;
    }

    public void setRetSysMsg(String retSysMsg) {
        this.retSysMsg = retSysMsg;
    }


    ResponseCode(String retKey) {
        this.retKey = retKey;
    }

    ResponseCode(String retCode, String retSysMsg) {
        this.retCode = retCode;
        this.retSysMsg = retSysMsg;
        this.retMsg = retSysMsg;
    }

    ResponseCode(String retCode, String retSysMsg, String retMsg) {
        this.retCode = retCode;
        this.retSysMsg = retSysMsg;
        this.retMsg = retMsg;
    }
}
