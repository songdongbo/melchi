package com.melchi.external.api.service.prod.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.melchi.external.api.service.prod.ProdService;
import com.melchi.external.common.model.BleApiException;
import com.melchi.external.module.prod.biz.ProdControlBiz;
import com.melchi.external.module.prod.dao.ProdControlDAO;
import com.melchi.external.module.prod.model.CategoriesInfo;
import com.melchi.external.module.prod.model.OrderDetailInfo;
import com.melchi.external.module.prod.model.OrderInfo;
import com.melchi.external.module.prod.model.ProdInfo;
import com.melchi.external.module.prod.model.ProdNoti;
import com.melchi.external.module.prod.model.ProdNotiReq;
import com.melchi.external.module.prod.model.ProdOpt;
import com.melchi.external.module.prod.model.ProdOptReq;
import com.melchi.external.module.prod.model.QnaAnswerInfo;
import com.melchi.external.module.prod.model.QnaProdInfo;
import com.melchi.external.module.prod.model.ShippingInfo;
import com.melchi.external.module.prod.model.ProdReq;

@Service
public class ProdServiceImpl implements ProdService {
	
	@Inject ProdControlBiz biz;
	@Inject ProdControlDAO dao;
	
	//상품 등록 
	@Override
	public void setProdInfoInsert(ProdInfo prodInfo) throws Exception {
		biz.setProdInfoInsert(prodInfo);
	}
	
	//상품 단건 조회 
	@Override
	public List<ProdInfo> getProdInfo(ProdInfo prodInfo, ProdOpt prodOpt, ProdNoti prodNoti) throws Exception {
		return biz.getProdInfo(prodInfo, prodOpt, prodNoti);
	}

	//상품 리스트 조회
	@Override
	public List<ProdInfo> getProdInfoList(ProdInfo prodInfo, ProdOpt prodOpt, ProdNoti prodNoti) throws Exception {
		return biz.getProdInfoList(prodInfo, prodOpt, prodNoti);
	}
	
	//상품옵션 등록 
	@Override
	public List<ProdOptReq>  setProdOptInsert(ProdOptReq prodOptReq, ProdOpt prodOpt) throws Exception {
		return biz.setProdOptInsert(prodOptReq, prodOpt);
	}
	
	//고시정보 등록 
	@Override
	public List<ProdNotiReq> setProdNotiInsert(ProdNotiReq prodNotiReq, ProdNoti prodNoti) throws Exception {
		return biz.setProdNotiInsert(prodNotiReq, prodNoti);
	}
	
	//상품 수정
	@Override
	public void setProdInfoUpdate(ProdInfo prodInfo) throws Exception {
		biz.setProdInfoUpdate(prodInfo);
	}
	
	//상품 부분수정
	@Override
	public void setProdInfoPartUpdate(ProdInfo prodInfo) throws Exception {
		biz.setProdInfoPartUpdate(prodInfo);
	}
	
	//상품 배송 부분 수정
	@Override
	public void setProdInfoPartxUpdate(ProdInfo prodInfo) throws Exception {
		biz.setProdInfoPartxUpdate(prodInfo);
	}
	
	//상품옵션 수정
	public List<ProdOptReq> setProdOptUpdate(ProdOptReq prodOptReq, ProdOpt prodOpt) throws Exception {
		return biz.setProdOptUpdate(prodOptReq, prodOpt);
	}
	
	//고시정보 수정
	public List<ProdNotiReq> setProdNotiUpdate(ProdNotiReq prodNotiReq, ProdNoti prodNoti) throws Exception {
		return biz.setProdNotiUpdate(prodNotiReq, prodNoti);
	}
	
	//상품옵션 부분수정
	@Override
	public List<ProdOptReq> setProdOptPartUpdate(ProdOptReq prodOptReq, ProdOpt prodOpt) throws Exception {
		return biz.setProdOptPartUpdate(prodOptReq, prodOpt);
	}
	
	//배송지등록
	@Override
	public void setShippingInsert(ShippingInfo shippingInfo) throws Exception {
		biz.setShippingInsert(shippingInfo);
	}
	
	//배송지수정
	@Override
	public void setShippingUpdate(ShippingInfo shippingInfo) throws Exception {
		biz.setShippingUpdate(shippingInfo);
	}

	//배송지조회
	@Override
	public Map<String, Object> getShipping(ShippingInfo shippingInfo) {
		return biz.getShipping(shippingInfo);
	}
	
	//카테고리조회
	@Override
	public List<CategoriesInfo> getCategories(CategoriesInfo categoriesInfo) {
		return biz.getCategories(categoriesInfo);
	}
	
	//주문조회
	@Override
	public Map<String, Object> getOrder(ProdReq prodReq) throws BleApiException  {
		return biz.getOrder(prodReq);
	}

	//주문조회
	@Override
	public Map<String, Object> getOrderList(ProdReq prodReq) throws BleApiException {
		return biz.getOrderList(prodReq);
	}
	
	//주문상세수정 주문 
	@Override
	public HashMap<String, Object> setOrderDeUpdate(OrderDetailInfo orderDetailInfo, String flag) throws BleApiException {
		return biz.setOrderDeUpdate(orderDetailInfo, flag);
	}	
	
	//상품/주문 문의 등록
	@Override
	public void setQnaProductInsert(QnaProdInfo qnaProdInfo) throws Exception {
		biz.setQnaProductInsert(qnaProdInfo);
		
	} 
	 
	//상품/주문 문의 수정 
	@Override
	public void setQnaProdUpdate(QnaProdInfo qnaProdInfo) throws Exception {
		biz.setQnaProdUpdate(qnaProdInfo); 		
	}

	@Override
	public List<QnaProdInfo> getQnaProductList(QnaProdInfo qnaProdInfo) {
		return biz.getQnaProductList(qnaProdInfo); 
	}	
	
	//상품/주문 답변 리스트
	@Override
	public List<QnaAnswerInfo> getQnaAnswerList(QnaAnswerInfo qnaAnswerInfo) {
		return biz.getQnaAnswerList(qnaAnswerInfo);
	}
	
	//상품/주문 답변 등록 
	@Override
	public void setQnaAnswerInsert(QnaAnswerInfo qnaAnswerInfo) throws Exception {
		biz.setQnaAnswerInsert(qnaAnswerInfo);
	}
	
	//상품/주문 답변 수정
	@Override
	public void setQnaAnswerUpdate(QnaAnswerInfo qnaAnswerInfo) throws Exception {
		biz.setQnaAnswerUpdate(qnaAnswerInfo);
	}
	
}
