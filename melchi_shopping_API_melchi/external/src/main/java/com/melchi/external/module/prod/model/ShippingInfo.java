package com.melchi.external.module.prod.model;

import lombok.Data;

@Data
public class ShippingInfo {
	
//	@NotNull
	private String sellercd;            //판매자 코드
	private String shippolicyNo;        //배송지                
	private String shippolicyNm;        //배송지 명             
	private String postcode1;           //출고지 우편번호1      
	private String postcode2;           //출고지 우편번호2      
	private String address1;            //출고지 주소           
	private String address2;            //출고지 상세주소       
	private String rnaddress1;          //출고지 도로명 주소    
	private String rnaddress2;          //출고지 도로명 상세주소
	private String returnpostcode1;     //반품주소지우편번호1   
	private String returnpostcode2;     //반품주소지우편번호2   
	private String returnaddress1;      //반품지 지번주소       
	private String returnaddress2;      //반품지 지번 상세주소  
	private String rnreturnaddress1;    //반품지 도로명 주소    
	private String rnreturnaddress2;    //반품지 도로명 상세주소
	private String useyn;               //사용여부              
//	private String regdate;             //등록일                
//	private String modifydate;          //수정일                
	private String apiindexing;         //멸치쇼핑 이관여부      
//	private String indexingdate;        //최종 수정일           	
	private String shippingcompanycode; //택배사코드           	
}
