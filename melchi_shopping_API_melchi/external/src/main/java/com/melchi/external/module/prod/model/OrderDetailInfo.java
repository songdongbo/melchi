package com.melchi.external.module.prod.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderDetailInfo {
	
//	@NotNull
	private String ordercd;                    //주문코드            
	private String productcd;                  //상품 코드           
	private String sellingprice;               //주문 금액           
	private String supplyprice;                //배송비총액          
	private String qty;                        //주문수량            
	private String amount;                     //상품 판매가         
	private String shipfinisheddate;           //배송 완료일         
	private String shippingno;                 //운송장 번호         
	private String delicomcd;                  //택배사 코드         
	private String shippingfeetype;            //배송비방식          
	private String shippingfee;                //배송비              
	private String shippingmethod;             //배송방법            
	private String shippingpersontel;          //배송기사연락처      
	private String shippingfeepaytype;         //배송비결제방식      
	private String chargedshippingfee;         //부과된배송비        
	private String importClearanceOrderder1;   //개인통관번호        
	private String importClearanceOrderder2;   //수입통관 주문자 정보
	private String intDelivYn;                 //국제배송 여부       
	private String intDelivStatCd;             //국제배송 상태코드   
	private String additionalshippingfee;      //도서산간 추가 배송비
	private String status;                     //주문상태 값         
	private String regdate;                    //등록일              
	private String modifydate;                 //수정일              
//	private String apiindexing;                //멸치쇼핑 이관여부   
//	private String indexingdate;               //최종 수정일
	private String cancelReason;               //취소사유        
	
    private List<OptionInfo> orderDetailOption;
}
