package com.melchi.external.module.prod.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OptionInfo {
	
//	@NotNull
	private String ordercd;         /* 주문코드           */
	private String productcd;       /* 상품 코드          */
	private String optionseq;       /* 옵션그룹핑일련번호 */
	private String optionprice;     /* 추가금액           */
	private String qty;             /* 주문수량           */
	private String optionitem;      /* 선택옵션           */      
	private String productoptioncd; /* 옵션코드           */      
}
