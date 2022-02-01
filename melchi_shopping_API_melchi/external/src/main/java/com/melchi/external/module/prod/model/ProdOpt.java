package com.melchi.external.module.prod.model;

import static com.melchi.external.common.BaseConst.Code.INVALID_ERROR;

import javax.validation.constraints.NotNull;

import com.melchi.external.common.model.BleApiException;

import lombok.Data;

@Data
public class ProdOpt {
	
	@NotNull
	private long productoptioncd;
	private long productcd;
	private int optiongroupno=1;	//기본값 1 (API사용안함)
	private String optiongroupname;
	private String optionitem;
	private String optionitemdetail;
	private int optionprice;
	private String isava;
	private int optionqty;
	
	public void validateSetProdOptInsert() throws Exception {
		
		if ("0".equals(String.valueOf(productcd))
		) {
			throw new BleApiException(INVALID_ERROR, "productcd 은 필수 값입니다."); 
		}
		
		if ("".equals(optiongroupname.trim())
				|| "".equals(optionitem.trim())
				|| "".equals(isava.trim())
		) {
			throw new BleApiException(INVALID_ERROR, "optiongroupname, optionitem, isava 은 필수 값입니다."); 
		}
		
		if ("0".equals(String.valueOf(optiongroupno))
		) {
			throw new BleApiException(INVALID_ERROR, "optiongroupno 은 필수 값입니다."); 
		}
	}
	
	public void validateSetProdOptUpdate() throws Exception {
		
		if ("0".equals(String.valueOf(productcd))
		) {
			throw new BleApiException(INVALID_ERROR, "productcd 은 필수 값입니다."); 
		}
		
		if ("".equals(optiongroupname.trim())
				|| "".equals(optionitem.trim())
				|| "".equals(isava.trim())
		) {
			throw new BleApiException(INVALID_ERROR, "optiongroupname, optionitem, isava 은 필수 값입니다."); 
		}
		
		if ("0".equals(String.valueOf(optiongroupno))
		) {
			throw new BleApiException(INVALID_ERROR, "optiongroupno 은 필수 값입니다."); 
		}
	}
	
	public void validateSetProdOptPartUpdate() throws Exception {
		
		if ("0".equals(String.valueOf(productoptioncd))
			|| "0".equals(String.valueOf(productcd))
		) {
			throw new BleApiException(INVALID_ERROR, "productoptioncd, productcd 은 필수 값입니다."); 
		}
		
		if ("".equals(isava.trim())
		) {
			throw new BleApiException(INVALID_ERROR, "isava 은 필수 값입니다."); 
		}
	}
	
	
}
