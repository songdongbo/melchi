package com.melchi.external.module.prod.model;

import javax.validation.constraints.NotNull;
import java.util.List;

import lombok.Data;

@Data
public class OrderInfo {
	
//	@NotNull
	private String ordercd;                 //주문코드                
	private String usercd;                  //판매자 코드             
	private String amount;                  //주문금액
	private String shipamount;              //배송비총액              
	private String discountamount;          //할인총액                
	private String usedshoppingpointamount; //사용한쇼핑포인트금액    
	private String usedanchovypointamount;  //사용한멸치포인트금액    
	private String usedemoneyamount;        //사용한이머니금액        
	private String recvperson;              //받는사람 이름           
	private String recvtel;                 //받는사람 연락처         
	private String recvhp;                  //받는사람 핸드폰 번호    
	private String recvpostcode1;           //받는사람 우편번호1      
	private String recvpostcode2;           //받는사람 우편번호2      
	private String recvaddress1;            //받는사람 지번주소       
	private String recvaddress2;            //받는사람 지번 상세주소  
	private String recvrnaddress1;          //받는사람 도로명주소     
	private String recvrnaddress2;          //받는사람 도로명 상세주소
	private String recvmessage;             //배송 메세지             
	private String orderdate;               //주문일자
	private String paymenttype;             //결제방식
	private String cardtype;                //카드종류
	private String interestchargeperiod;    //할부기간
	private String banktype;                //은행종류
	private String depositor;               //입금자명
	private String depositdeadline;         //입금기한
	private String refunddate;              //결제취소처리일자        
	private String refundamount1;           //신용카드취소금액        
	private String refundamount2;           //가상계좌취소금액        
	private String refundamount3;           //이머니취소금액          
	private String refundamount4;           //멸치포인트취소금액      
	private String refundamount5;           //쇼핑포인트취소금액      
	private String refundaccountcd;         //결제취소처리자          
	private String orderchannel;            //                        
	private String refundbankcd;            //                        
	private String refundaccountnum;        //                        
	private String refunddepositor;         //                        
	private String outsidepost;             //도서산간 배송 여부
	private String regdate;                 //등록일                  
	private String modifydate;              //수정일                  
//	private String apiindexing;             //멸치쇼핑 이관여부       
//	private String indexingdate;            //최종 수정일
	
    private List<OrderDetailInfo> orderDetail;
}
