package com.melchi.external.module.prod.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.melchi.external.module.prod.model.CategoriesInfo;
import com.melchi.external.module.prod.model.OptionInfo;
import com.melchi.external.module.prod.model.OrderDetailInfo;
import com.melchi.external.module.prod.model.OrderInfo;
import com.melchi.external.module.prod.model.ProdInfo;
import com.melchi.external.module.prod.model.ProdNoti;
import com.melchi.external.module.prod.model.ProdNotiReq;
import com.melchi.external.module.prod.model.ProdOpt;
import com.melchi.external.module.prod.model.ProdOptReq;
import com.melchi.external.module.prod.model.ProdReq;
import com.melchi.external.module.prod.model.QnaAnswerInfo;
import com.melchi.external.module.prod.model.QnaProdInfo;
import com.melchi.external.module.prod.model.ShippingInfo;

public interface ProdControlDAO {

	//상품 등록 
	public int setProdInfoInsert(@Param("prodInfo") ProdInfo prodInfo) throws Exception;
	
	//상품 조회
	public List<ProdInfo> getProdInfo(ProdInfo prodInfo) throws Exception;
	
	//상품 리스트 조회
	public List<ProdInfo> getProdInfoList(ProdInfo prodInfo) throws Exception;
	
	//상품 옵션 조회
	public List<ProdOpt> getProdOptList(ProdOpt prodOpt) throws Exception;
	
	//고시 정보 조회
	public List<ProdNoti> getProdNotiList(ProdNoti prodNoti) throws Exception;
	
	//상품 옵션 코드 조회
	public List<ProdOptReq> getProdOptCodeList(ProdOpt prodOpt) throws Exception;
	
	//고시정보 코드 조회
	public List<ProdNotiReq> getProdNotiCodeList(ProdNoti prodNoti) throws Exception;
		
	//상품 등록 번호 조회
	public long setProdInfoSelect(@Param("prodInfo") ProdInfo prodInfo) throws Exception;
	
	//배송지 번호 조회
	public int getShippolicyNoSelect(@Param("prodInfo") ProdInfo prodInfo) throws Exception;
	
	//카테고리 번호 조회
	public int getCatecdSelect(@Param("prodInfo") ProdInfo prodInfo) throws Exception;
	
	//상품옵션 등록 
	public int setProdOptInsert(@Param("prodOpt") ProdOpt prodOpt) throws Exception;
	
	//고시정보 등록 
	public int setProdNotiInsert(@Param("prodNoti") ProdNoti prodNoti) throws Exception;
	
	//상품 옵션 등록 번호 조회
	public long setProdOptSelect(ProdOpt prodOpt) throws Exception;
	
	//상품 옵션 번호 조회
	public int getOptioncdSelect(@Param("prodOpt") ProdOpt prodOpt) throws Exception;
	
	//고시 정보 번호 조회
	public int getNoticdSelect(@Param("prodNoti") ProdNoti prodNoti) throws Exception;
	
	//상품 수정
	public int setProdInfoUpdate(@Param("prodInfo") ProdInfo prodInfo) throws Exception;
	
	//상품 부분수정
	public int setProdInfoPartUpdate(@Param("prodInfo") ProdInfo prodInfo) throws Exception;
	
	//고시정보 수정  
	public int setProdNotiUpdate(@Param("prodNoti") ProdNoti prodNoti) throws Exception;
	
	//상품 배송 부분 수정
	public int setProdInfoPartxUpdate(@Param("prodInfo") ProdInfo prodInfo) throws Exception;

	//상품옵션 수정
	public int setProdOptUpdate(@Param("prodOpt") ProdOpt prodOpt) throws Exception;
	
	//상품옵션 부분수정
	public int setProdOptPartUpdate(@Param("prodOpt") ProdOpt prodOpt) throws Exception;

	//배송지등록
	public int setShippingInsert(ShippingInfo shippingInfo);
	
	//배송지채번값조회
	public String getMaxShippolicyNo(ShippingInfo shippingInfo);

	//배송지수정
	public int setShippingUpdate(ShippingInfo shippingInfo);

	//배송지조회
	public Map<String, Object> getShipping(ShippingInfo shippingInfo);
	
	//카테고리조회
	public List<CategoriesInfo> getCategories(CategoriesInfo categoriesInfo);
	
	//주문조회
	public List<OrderInfo> getOrderList(ProdReq prodReq);

	//주문상세조회
	public List<OrderDetailInfo> getOrderDetail(ProdReq prodReq);
	
	//주문옵션조회
	public List<OptionInfo> getOrderDeOpts(OrderDetailInfo orderDetailInfo);
	
	//주문상세수정
	public int setOrderDeUpdate(ProdReq prodReq);

	//상품/주문 문의 등록
	public int setQnaProductInsert(QnaProdInfo qnaProdInfo) throws Exception;

	//상품/주문 문의 수정   
	public int setQnaProductUpdate(QnaProdInfo qnaProdInfo) throws Exception;

	//상품/주문 문의 목록 조회
	public List<QnaProdInfo> getQnaProductList(QnaProdInfo qnaProdInfo);
	
	//상품/주문 답변 리스트
	public List<QnaAnswerInfo> getQnaAnswerList(QnaAnswerInfo qnaAnswerInfo);
	
	//상품/주문 답변 번호 조회
	public long getQnaAnswerSelect(QnaAnswerInfo qnaAnswerInfo) throws Exception;

	//상품/주문 답변 등록 
	public int setQnaAnswerInsert(@Param("qnaAnswerInfo") QnaAnswerInfo qnaAnswerInfo) throws Exception;
	
	//상품/주문 답변 수정
	public int setQnaAnswerUpdate(@Param("qnaAnswerInfo") QnaAnswerInfo qnaAnswerInfo) throws Exception;

}


