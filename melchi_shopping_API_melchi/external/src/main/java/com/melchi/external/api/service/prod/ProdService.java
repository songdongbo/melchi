package com.melchi.external.api.service.prod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.melchi.external.common.model.BleApiException;
import com.melchi.external.module.prod.model.CategoriesInfo;
import com.melchi.external.module.prod.model.OrderDetailInfo;
import com.melchi.external.module.prod.model.OrderInfo;
import com.melchi.external.module.prod.model.ProdReq;
import com.melchi.external.module.prod.model.ProdInfo;
import com.melchi.external.module.prod.model.ProdNoti;
import com.melchi.external.module.prod.model.ProdNotiReq;
import com.melchi.external.module.prod.model.ProdOpt;
import com.melchi.external.module.prod.model.ProdOptReq;
import com.melchi.external.module.prod.model.QnaAnswerInfo;
import com.melchi.external.module.prod.model.QnaProdInfo;
import com.melchi.external.module.prod.model.ShippingInfo;

public interface ProdService {

	//상품 등록  
	public void setProdInfoInsert(ProdInfo prodInfo) throws Exception;
	
	//상품 단건 조회 
	public List<ProdInfo> getProdInfo(ProdInfo prodInfo, ProdOpt prodOpt, ProdNoti prodNoti) throws Exception;
	
	//상품 리스트 조회
	public List<ProdInfo> getProdInfoList(ProdInfo prodInfo, ProdOpt prodOpt, ProdNoti prodNoti) throws Exception;	
	
	//상품옵션 등록 
	public List<ProdOptReq> setProdOptInsert(ProdOptReq prodOptReq, ProdOpt prodOpt) throws Exception;
	
	//고시정보 등록 
	public List<ProdNotiReq> setProdNotiInsert(ProdNotiReq prodNotiReq, ProdNoti prodNoti) throws Exception;
	
	//상품 수정
	public void setProdInfoUpdate(ProdInfo prodInfo) throws Exception;
	
	//상품 부분수정
	public void setProdInfoPartUpdate(ProdInfo prodInfo) throws Exception;
	
	//상품 배송 부분 수정
	public void setProdInfoPartxUpdate(ProdInfo prodInfo) throws Exception;
	
	//상품옵션 수정
	public List<ProdOptReq> setProdOptUpdate(ProdOptReq prodOptReq, ProdOpt prodOpt) throws Exception;
	
	//고시정보 수정
	public List<ProdNotiReq> setProdNotiUpdate(ProdNotiReq prodNotiReq, ProdNoti prodNoti) throws Exception;
		
	//상품옵션 부분수정
	public List<ProdOptReq> setProdOptPartUpdate(ProdOptReq prodOptReq, ProdOpt prodOpt) throws Exception;
	
	//배송지등록
	public void setShippingInsert(ShippingInfo shippingInfo) throws Exception;
	
	//배송지수정
	public void setShippingUpdate(ShippingInfo shippingInfo) throws Exception;

	//배송지조회
	public Map<String, Object> getShipping(ShippingInfo shippingInfo);

	//카테고리조회
	public List<CategoriesInfo> getCategories(CategoriesInfo categoriesInfo);

	//주문조회
	public Map<String, Object> getOrder(ProdReq prodReq) throws BleApiException;
	
	//주문조회리스트
	public Map<String, Object> getOrderList(ProdReq prodReq) throws BleApiException;
	
	//주문상세수정 
	public HashMap<String, Object> setOrderDeUpdate(OrderDetailInfo orderDetailInfo, String flag) throws BleApiException;
	
	//상품/주문 문의 등록 
	public void setQnaProductInsert(QnaProdInfo qnaProdInfo) throws Exception;

	//상품/주문 문의 수정  
	public void setQnaProdUpdate(QnaProdInfo qnaProdInfo) throws Exception;
	
	//상품/주문 문의 리스트
	public List<QnaProdInfo> getQnaProductList(QnaProdInfo qnaProdInfo);	

	//상품/주문 답변 리스트 
	public List<QnaAnswerInfo> getQnaAnswerList(QnaAnswerInfo qnaAnswerInfo);

	//상품/주문 답변 등록 
	public void setQnaAnswerInsert(QnaAnswerInfo qnaAnswerInfo) throws Exception;
	
	//상품/주문 답변 수정
	public void setQnaAnswerUpdate(QnaAnswerInfo qnaAnswerInfo) throws Exception;
	
}

