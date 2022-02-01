package com.melchi.external.module.prod.model;

import javax.validation.constraints.NotNull;

import com.melchi.external.common.model.BleApiException;
import static  com.melchi.external.common.BaseConst.Code.INVALID_ERROR;

import java.util.List;

import lombok.Data;

@Data
public class ProdInfo {

	@NotNull
	private long productcd;
	private int catecd;
	private int sellercd;
	private String status;
	private String productname;
	private String productviewcd;
	private String sellerproductcd;
	private String manufacname;
	private String originname;
	private String isoriginnamedetailref;
	private String brandname;
	private String author;
	private String isbn;
	private int isadultauth;
	private String saledisplaytype;
	private int istaxed;
	private String healthsalespernum;
	private String medicalsalespernum;
	private long sellingprice;
	private int prdqty;
	private int isusedproductoption;
	private long shippolicy_no;
	private String shippingmethod;
	private String shippingfeetype;
	private int shippingfee;
	private int additionalshippingfee;
	private String shippingfeepaytype;
	private int freeshippingamount;
	private int returnshippingfee;
	private int changeshippingfee;
	private int salelimitcnt;
	private String contents;
	private String overseas_goods_sales_yn;
	private String startdatedue;
	private String enddatedue;
	private String imgpatha;
	private String imgpathb;
	private String imgpathc;
	private String regdate;
	private String brandcd;
	private List<ProdOpt> op1_data;
	private List<ProdOpt> op2_data;
	private List<ProdOpt> op3_data;
	
	private List<ProdNoti> noti_data;
	
	//상품등록
	public void validateSetProdInfoInsert() throws Exception {
		
		if ("0".equals(String.valueOf(catecd))
				|| "0".equals(String.valueOf(sellercd))
		) {
			throw new BleApiException(INVALID_ERROR, "catecd, sellercd은 필수 값입니다."); 
		}
		
		if ("".equals(status.trim())
				|| "".equals(productname.trim())
				|| "".equals(manufacname.trim())
				|| "".equals(originname.trim())
				|| "".equals(brandname.trim())
				|| "".equals(overseas_goods_sales_yn.trim())
		) {
			throw new BleApiException(INVALID_ERROR, "status, productname, manufacname, originname, brandname, overseas_goods_sales_yn 은 필수 값입니다."); 
		}
		
		if ("0".equals(String.valueOf(sellingprice))
				|| "0".equals(String.valueOf(prdqty))
				|| "0".equals(String.valueOf(shippolicy_no))
		) {
			throw new BleApiException(INVALID_ERROR, "sellingprice, prdqty, shippolicy_no 은 필수 값입니다."); 
		}
	}
	
	//상품단건조회
	public void validateGetProdInfo() throws Exception {
		
		if ("0".equals(String.valueOf(productcd))) {
			throw new BleApiException(INVALID_ERROR, "productcd 은 필수 값입니다."); 
		}
	}
	
	//상품리스트조회
	public void validateGetProdInfoList() throws Exception {
		
		if ("".equals(regdate.trim())) {
			throw new BleApiException(INVALID_ERROR, "productcd 은 필수 값입니다."); 
		}
	}
	
	
	//상품전체수정
	public void validateSetProdInfoUpdate() throws Exception {
		
		if ("0".equals(String.valueOf(productcd))
				|| "0".equals(String.valueOf(catecd))
				|| "0".equals(String.valueOf(sellercd))
		) {
			throw new BleApiException(INVALID_ERROR, "productcd, catecd, sellercd 은 필수 값입니다."); 
		}
		
		if ("".equals(status.trim())
				|| "".equals(productname.trim())
				|| "".equals(manufacname.trim())
				|| "".equals(originname.trim())
				|| "".equals(brandname.trim())
				|| "".equals(overseas_goods_sales_yn.trim())
		) {
			throw new BleApiException(INVALID_ERROR, "status, productname, manufacname, originname, brandname, overseas_goods_sales_yn 은 필수 값입니다."); 
		}
		
		if ("0".equals(String.valueOf(sellingprice))
				|| "0".equals(String.valueOf(prdqty))
				|| "0".equals(String.valueOf(shippolicy_no))
		) {
			throw new BleApiException(INVALID_ERROR, "sellingprice, prdqty, shippolicy_no 은 필수 값입니다."); 
		}
	}
	
	//상품부분수정
	public void validateSetProdInfoPartUpdate() throws Exception {
		
		if ("0".equals(String.valueOf(productcd))
				|| "0".equals(String.valueOf(catecd))
				|| "0".equals(String.valueOf(sellercd))
		) {
			throw new BleApiException(INVALID_ERROR, "productcd, catecd, sellercd 은 필수 값입니다."); 
		}
		
		if ("".equals(status.trim())
		) {
			throw new BleApiException(INVALID_ERROR, "status 은 필수 값입니다."); 
		}
		
		if ("0".equals(String.valueOf(sellingprice))
				|| "0".equals(String.valueOf(prdqty))
		) {
			throw new BleApiException(INVALID_ERROR, "sellingprice, prdqty 은 필수 값입니다."); 
		}
	}
	
	//상품부분수정x
	public void validateSetProdInfoPartxUpdate() throws Exception {
		
		if ("0".equals(String.valueOf(productcd))
				|| "0".equals(String.valueOf(catecd))
				|| "0".equals(String.valueOf(sellercd))
		) {
			throw new BleApiException(INVALID_ERROR, "productcd, catecd, sellercd 은 필수 값입니다."); 
		}
		
		if ("0".equals(String.valueOf(shippolicy_no))
		) {
			throw new BleApiException(INVALID_ERROR, "shippolicy_no 은 필수 값입니다."); 
		}
	}
	
	
}
