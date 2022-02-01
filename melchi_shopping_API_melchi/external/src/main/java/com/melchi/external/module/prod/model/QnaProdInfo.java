package com.melchi.external.module.prod.model;

import lombok.Data;

@Data
public class QnaProdInfo {
	
	private String seq;          //문의번호    
	private String subject;      //QnA 제목    
	private String contents;     //질문 내용   
	private String regdate;      //등록일      
	private String writername;   //작성자명    
	private String writetel;    //작성자연락처
	private String productcd;    //상품코드    
	private String email;        //이메일      
	private String qnaTyp;       //문의 유형 [QNA_TYP][10:상품,20:배송,30:반품,40:교환,50:환불,90:기타]
	private String ordercd;      //주문코드
	private String answeryn;      //답변여부      
	 
	 
	 
	//조회 조건 필드 
	private String startDate;       //시작일자
	private String endDate;          //종료일자     
}
  