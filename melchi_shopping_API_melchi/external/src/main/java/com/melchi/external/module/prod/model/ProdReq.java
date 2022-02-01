package com.melchi.external.module.prod.model;

import java.util.List;

import lombok.Data;

@Data
public class ProdReq {
	//주문상세수정용
	private String ordercd;      //주문코드
	private String productcd;    //상품 코드
	private String status;       //주문상태 값
	private String shippingno;   //운송장 번호
	private String delicomcd;    //택배사 코드
	private String cancelReason; //취소사유 
	
	//주문조회용
	private String orderdateStart;          //주문일자시작                
	private String orderdateEnd;            //주문일자종료           
    private List<OrderDetailInfo> orderDetail;	
	

}
