package com.melchi.external.module.prod.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CategoriesInfo {
	
//	@NotNull
	private String catecd;       //카테고리 코드    
	private String refcatecd;    //부모카테고리     
	private String categoryname; //카테고리명       
	private String step;         //카테고리 단계    
	private String sortno;       //화면표시순서     
	private String datereg;      //등록일자         
	private String catecd1;      //1차 카테고리 코드
	private String catecd2;      //2차 카테고리 코드
	private String catecd3;      //3차 카테고리 코드
	private String catecd4;      //4차 카테고리 코드
	private String fullCatecd;   //카테고리전체     
	private String catenm1;      //1차 카테고리 명  
	private String catenm2;      //2차 카테고리 명  
	private String catenm3;      //3차 카테고리 명  
	private String catenm4;      //4차 카테고리 명  
	private String fullSortno;   //
	
}
