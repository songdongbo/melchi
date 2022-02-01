package com.melchi.external.module.prod.biz;

import static com.melchi.external.common.BaseConst.Code.INVALID_ERROR;

//import static com.melchi.external.common.BaseConst.Code.Code.INVALID_ERROR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.melchi.external.common.ResponseCode;
import com.melchi.external.common.ResponseTemplate;
import com.melchi.external.common.BaseConst.Code;
import com.melchi.external.common.model.BleApiException;
import com.melchi.external.common.util.StringUtil;
import com.melchi.external.module.prod.dao.ProdControlDAO;
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

@Component
public class ProdControlBiz {
	
	private final Logger LOGGER = LoggerFactory.getLogger(ProdControlBiz.class);

	
	@Inject ProdControlDAO dao;
	
	//상품 등록 
	@Transactional(rollbackFor = Exception.class)
	public void setProdInfoInsert(ProdInfo prodInfo) throws Exception {
		int result = 0;
		long productcd = 0;
		int shippolicyno = 0;
		int catecd = 0;
		
		//배송지 번호 조회
		shippolicyno = dao.getShippolicyNoSelect(prodInfo);
		if(shippolicyno == 0) {
			throw new BleApiException(Code.SHIPNO_VAL, Code.SHIPNO_VAL_MSG);
		}
		
		//카테고리 번호 조회
		catecd = dao.getCatecdSelect(prodInfo);
		if(catecd == 0) {
			throw new BleApiException(Code.CATEGORY_VAL, Code.CATEGORY_VAL_MSG);
		}
		
		//유료배송인데 배송비0원일 경우 Error처리
		String shippingfeetype = prodInfo.getShippingfeetype();
		int shippingfee = prodInfo.getShippingfee();
		
		if(!shippingfeetype.equals("01") && shippingfee == 0) {
			throw new BleApiException(INVALID_ERROR, "유료배송은 배송비가 0원이 될 수 없습니다. 배송비 확인 부탁드립니다.");
		}
		
		result = dao.setProdInfoInsert(prodInfo);	
		
		if(result > 0) {
			try {
				productcd = dao.setProdInfoSelect(prodInfo);
				prodInfo.setProductcd(productcd);
				
			} catch (DuplicateKeyException e) {
				throw new BleApiException(Code.DUPLICATE, Code.DUPLICATE_MSG);
			}
		}
	}
	
	//상품 단건 조회 
	public List<ProdInfo> getProdInfo(ProdInfo prodInfo, ProdOpt prodOpt, ProdNoti prodNoti) throws Exception {
		return rtnProdInfoList(prodInfo, prodOpt, prodNoti);
	}

	//상품 리스트 조회 
	public List<ProdInfo> getProdInfoList(ProdInfo prodInfo, ProdOpt prodOpt, ProdNoti prodNoti) throws Exception {
		return rtnProdInfoList(prodInfo, prodOpt, prodNoti);
	}

	//상품조회 - 옵션조회
	@Transactional
	public List<ProdInfo> rtnProdInfoList(ProdInfo prodInfo, ProdOpt prodOpt, ProdNoti prodNoti) throws Exception {
		
		List<ProdInfo> rtnProdInfoList = new ArrayList<ProdInfo>();
		
    	List<ProdInfo> prodList = new ArrayList<ProdInfo>(); 
    	prodList = dao.getProdInfoList(prodInfo);
    	
    	if(prodList == null) {
    		throw new BleApiException(INVALID_ERROR, "productcd 필수 값입니다.");
    	}else {

	        for(ProdInfo prodInfoMap : prodList) {

	        	List<ProdOpt> prodOptList1 = new ArrayList<ProdOpt>();
	        	List<ProdOpt> prodOptList2 = new ArrayList<ProdOpt>();
	        	List<ProdOpt> prodOptList3 = new ArrayList<ProdOpt>();
	        	List<ProdNoti> prodNotiList = new ArrayList<ProdNoti>();
	        	
	        	prodOpt.setOptiongroupno(1);
	        	prodOpt.setProductcd(prodInfoMap.getProductcd());
	        	prodOptList1 = dao.getProdOptList(prodOpt);
	        	prodInfoMap.setOp1_data(prodOptList1);
	        	
	        	prodOpt.setOptiongroupno(2);
	        	prodOpt.setProductcd(prodInfoMap.getProductcd());
	        	prodOptList2 = dao.getProdOptList(prodOpt);
	        	prodInfoMap.setOp2_data(prodOptList2);
	        	
	        	prodOpt.setOptiongroupno(3);
	        	prodOpt.setProductcd(prodInfoMap.getProductcd());
	        	prodOptList3 = dao.getProdOptList(prodOpt);
	        	prodInfoMap.setOp3_data(prodOptList3);
	        	
	        	prodNoti.setProductcd(prodInfoMap.getProductcd());
	        	prodNotiList = dao.getProdNotiList(prodNoti);
	        	prodInfoMap.setNoti_data(prodNotiList);
	        	
	        	rtnProdInfoList.add(prodInfoMap);
	        }
    	}
        
		return rtnProdInfoList;
	}
	
	//상품옵션 등록 
	@Transactional(rollbackFor = Exception.class)
	public List<ProdOptReq> setProdOptInsert(ProdOptReq prodOptReq, ProdOpt prodOpt) throws Exception {
		
		int result = 0;
		int optioncd = 0;
		
		List<ProdOpt> op1_data = prodOptReq.getOp1_data();
		List<ProdOpt> op2_data = prodOptReq.getOp2_data();
		List<ProdOpt> op3_data = prodOptReq.getOp3_data();
		
		if(op1_data == null) {
		}else {
			for (ProdOpt data1 : op1_data) {
				data1.setProductcd(prodOptReq.getProductcd());
				/*if(data1.getOptiongroupname().equals("") || data1.getOptionitem().equals("") || data1.getIsava().equals(""))
				{
				}else {*/
					
					//상품등록여부조회
					optioncd = dao.getOptioncdSelect(data1);
					if(optioncd > 0) {
						throw new BleApiException(Code.INVALID_ERROR, Code.REGI_OPT_VAL_MSG);
					}
					data1.setOptiongroupno(1);
					result = dao.setProdOptInsert(data1);
				/*}*/
			}
		}
		
		if(op2_data == null) {
		}else {
			for (ProdOpt data2 : op2_data) {
				
				data2.setProductcd(prodOptReq.getProductcd());
				/*if(data2.getOptiongroupname().equals("") || data2.getOptionitem().equals("") || data2.getIsava().equals(""))
				{
				}else {*/
					//상품등록여부조회
					optioncd = dao.getOptioncdSelect(data2);
					if(optioncd > 0) {
						throw new BleApiException(Code.REGI_OPT_VAL, Code.REGI_OPT_VAL_MSG);
					}
					
					data2.setOptiongroupno(2);
					result = dao.setProdOptInsert(data2);
				/*}*/
			}
		}
		
		if(op3_data == null) {
		}else {
			for (ProdOpt data3 : op3_data) {
				
				data3.setProductcd(prodOptReq.getProductcd());
				/*if(data3.getOptiongroupname().equals("") || data3.getOptionitem().equals("") || data3.getIsava().equals(""))
				{
				}else {*/
					//상품등록여부조회
					optioncd = dao.getOptioncdSelect(data3);
					if(optioncd > 0) {
						throw new BleApiException(Code.REGI_OPT_VAL, Code.REGI_OPT_VAL_MSG);
					}
					
					data3.setOptiongroupno(3);
					result = dao.setProdOptInsert(data3);
				/*}*/
			}
		}

		//리턴 정보
		List<ProdOptReq> rtnProdOptReqList = new ArrayList<ProdOptReq>();
		prodOpt.setProductcd(prodOptReq.getProductcd());
		
		//옵션정보조회
    	List<ProdOptReq> prodOptReq2 = new ArrayList<ProdOptReq>();
    	prodOptReq2 = dao.getProdOptCodeList(prodOpt);
    	
    	if(prodOptReq2 == null) {
    		throw new BleApiException(INVALID_ERROR, "productcd 필수 값입니다.");
    	}else {
	    	
    		for(ProdOptReq prodOptReqMap : prodOptReq2) {

		    	List<ProdOpt> prodOptList1 = new ArrayList<ProdOpt>();
		    	List<ProdOpt> prodOptList2 = new ArrayList<ProdOpt>();
		    	List<ProdOpt> prodOptList3 = new ArrayList<ProdOpt>();
		    	
		    	prodOpt.setOptiongroupno(1);
		    	prodOptList1 = dao.getProdOptList(prodOpt);
		    	prodOptReqMap.setOp1_data(prodOptList1);
		    	
		    	prodOpt.setOptiongroupno(2);
		    	prodOptList2 = dao.getProdOptList(prodOpt);
		    	prodOptReqMap.setOp2_data(prodOptList2);
		    	
		    	prodOpt.setOptiongroupno(3);
		    	prodOptList3 = dao.getProdOptList(prodOpt);
		    	prodOptReqMap.setOp3_data(prodOptList3);
			    	
			    rtnProdOptReqList.add(prodOptReqMap);
    		}
    	} 
		return rtnProdOptReqList;
	}
	
	//고시정보 등록 
	@Transactional(rollbackFor = Exception.class)
	public List<ProdNotiReq> setProdNotiInsert(ProdNotiReq prodNotiReq, ProdNoti prodNoti) throws Exception {
		
		int result = 0;
		int noticd = 0;
		
		List<ProdNoti> noti_data = prodNotiReq.getNoti_data();
		
		if(noti_data == null) {
		}else {
			for (ProdNoti data1 : noti_data) {
				
				data1.setProductcd(prodNotiReq.getProductcd());
				if(data1.getItem().equals("") || data1.getContents().equals("") || data1.getIsava().equals(""))
				{
				}else {
					
					//고시정보등록여부조회 
					noticd = dao.getNoticdSelect(data1);
					if(noticd > 0) {
						throw new BleApiException(Code.INVALID_ERROR, Code.REGI_OPT_VAL_MSG);
					}
					result = dao.setProdNotiInsert(data1);
				}
			}
		}

		//리턴 정보
		List<ProdNotiReq> rtnProdNotiReqList = new ArrayList<ProdNotiReq>();
		prodNoti.setProductcd(prodNotiReq.getProductcd());
		
		//옵션정보조회
    	List<ProdNotiReq> prodNotiReq2 = new ArrayList<ProdNotiReq>();
    	prodNotiReq2 = dao.getProdNotiCodeList(prodNoti);
    	
    	if(prodNotiReq2 == null) {
    		throw new BleApiException(INVALID_ERROR, "productcd 필수 값입니다.");
    	}else {
	    	
    		for(ProdNotiReq prodNotiReqMap : prodNotiReq2) {
			
		    	List<ProdNoti> prodNotiList1 = new ArrayList<ProdNoti>();
		    	
		    	prodNotiList1 = dao.getProdNotiList(prodNoti);
		    	prodNotiReqMap.setNoti_data(prodNotiList1);
		    	
		    	rtnProdNotiReqList.add(prodNotiReqMap);
    		}
    	} 
		return rtnProdNotiReqList;
	}
	
	//상품 수정
	@Transactional(rollbackFor = Exception.class)
	public void setProdInfoUpdate(ProdInfo prodInfo) throws Exception {
		
		int result = 0;
		int shippolicyno = 0;
		int catecd = 0;
		
		//배송지 번호 조회
		shippolicyno = dao.getShippolicyNoSelect(prodInfo);
		if(shippolicyno == 0) {
			throw new BleApiException(Code.SHIPNO_VAL, Code.SHIPNO_VAL_MSG);
		}
		
		//카테고리 번호 조회
		catecd = dao.getCatecdSelect(prodInfo);
		if(catecd == 0) {
			throw new BleApiException(Code.CATEGORY_VAL, Code.CATEGORY_VAL_MSG);
		}
		
		try {
			//prodInfo.setContents(prodInfo.getContents().replaceAll("\"", "'"));
			result = dao.setProdInfoUpdate(prodInfo);	
			
			if(result == 0) {
				throw new BleApiException(Code.INVALID_ERROR, "파마미터정보가 잘못되었습니다.");
			}
			
			
		} catch (DuplicateKeyException e) {
			throw new BleApiException(Code.DUPLICATE, Code.DUPLICATE_MSG);
		}
	}
	
	//상품 부분수정
	@Transactional
	public void setProdInfoPartUpdate(ProdInfo prodInfo) throws Exception {
		
		@SuppressWarnings("unused")
		int result = 0;
		int catecd = 0;
		
		//카테고리 번호 조회
		catecd = dao.getCatecdSelect(prodInfo);
		if(catecd == 0) {
			throw new BleApiException(Code.CATEGORY_VAL, Code.CATEGORY_VAL_MSG);
		}
		
		try {
			result = dao.setProdInfoPartUpdate(prodInfo);
		} catch (DuplicateKeyException e) {
			throw new BleApiException(Code.DUPLICATE, Code.DUPLICATE_MSG);
		}
	}
	
	//상품 배송 부분 수정
	@Transactional
	public void setProdInfoPartxUpdate(ProdInfo prodInfo) throws Exception {
		int result = 0;
		int shippolicyno = 0;
		int catecd = 0;
		
		//배송지 번호 조회
		shippolicyno = dao.getShippolicyNoSelect(prodInfo);
		if(shippolicyno == 0) {
			throw new BleApiException(Code.SHIPNO_VAL, Code.SHIPNO_VAL_MSG);
		}
		
		//카테고리 번호 조회
		catecd = dao.getCatecdSelect(prodInfo);
		if(catecd == 0) {
			throw new BleApiException(Code.CATEGORY_VAL, Code.CATEGORY_VAL_MSG);
		}
		
		try {
			result = dao.setProdInfoPartxUpdate(prodInfo);	
		} catch (DuplicateKeyException e) {
			throw new BleApiException(Code.DUPLICATE, Code.DUPLICATE_MSG);
		}
	}
	
	//고시정보 수정
	@Transactional
	public List<ProdNotiReq> setProdNotiUpdate(ProdNotiReq prodNotiReq, ProdNoti prodNoti) throws Exception {
		int result = 0;
		
		List<ProdNoti> noti_data = prodNotiReq.getNoti_data();
		
		if(noti_data == null) {
		}else {
			for (ProdNoti data1 : noti_data) {
				
				data1.setProductcd(prodNotiReq.getProductcd());
				if(data1.getSeq().equals("") || data1.getItem().equals("") || data1.getContents().equals("") || data1.getIsava().equals(""))
				{
				}else {
					result = dao.setProdNotiUpdate(data1);	
				}
			}
		}
		
		//리턴 정보
		List<ProdNotiReq> rtnProdNotiReqList = new ArrayList<ProdNotiReq>();
		prodNoti.setProductcd(prodNotiReq.getProductcd());
		
    	List<ProdNotiReq> prodNotiReq2 = new ArrayList<ProdNotiReq>();
    	prodNotiReq2 = dao.getProdNotiCodeList(prodNoti);
    	
    	if(prodNotiReq2 == null) {
    		throw new BleApiException(INVALID_ERROR, "productcd 필수 값입니다.");
    	}else {
	    	
    		for(ProdNotiReq prodNotiReqMap : prodNotiReq2) {
			
		    	List<ProdNoti> prodNotiList1 = new ArrayList<ProdNoti>();
		    	
		    	prodNotiList1 = dao.getProdNotiList(prodNoti);
		    	prodNotiReqMap.setNoti_data(prodNotiList1);
		    		
			    rtnProdNotiReqList.add(prodNotiReqMap);
    		}
    	} 
		return rtnProdNotiReqList;
	}
	
	//상품옵션 수정
	@Transactional
	public List<ProdOptReq> setProdOptUpdate(ProdOptReq prodOptReq, ProdOpt prodOpt) throws Exception {
		
		int result = 0;
		
		//옵션 처리
		List<ProdOpt> op1_data = prodOptReq.getOp1_data();
		List<ProdOpt> op2_data = prodOptReq.getOp2_data();
		List<ProdOpt> op3_data = prodOptReq.getOp3_data();
		
		if(op1_data == null) {
		}else {
			for (ProdOpt data1 : op1_data) {
				
				data1.setProductcd(prodOptReq.getProductcd());
				if(data1.getOptiongroupname().equals("") || data1.getOptionitem().equals("") || data1.getIsava().equals(""))
				{
				}else {
					data1.setOptiongroupno(1);
					result = dao.setProdOptUpdate(data1);	
				}
			}
		}
		
		if(op2_data == null) {
		}else {
			for (ProdOpt data2 : op2_data) {
				
				data2.setProductcd(prodOptReq.getProductcd());
				if(data2.getOptiongroupname().equals("") || data2.getOptionitem().equals("") || data2.getIsava().equals(""))
				{
				}else {
					data2.setOptiongroupno(2);
					result = dao.setProdOptUpdate(data2);
				}
			}
		}
		
		if(op3_data == null) {
		}else {
			for (ProdOpt data3 : op3_data) {
				
				data3.setProductcd(prodOptReq.getProductcd());
				if(data3.getOptiongroupname().equals("") || data3.getOptionitem().equals("") || data3.getIsava().equals(""))
				{
				}else {
					data3.setOptiongroupno(3);
					result = dao.setProdOptUpdate(data3);
				}
			}
		}
		
		//리턴 정보
		List<ProdOptReq> rtnProdOptReqList = new ArrayList<ProdOptReq>();
		prodOpt.setProductcd(prodOptReq.getProductcd());
		
		//옵션정보조회
    	List<ProdOptReq> prodOptReq2 = new ArrayList<ProdOptReq>();
    	prodOptReq2 = dao.getProdOptCodeList(prodOpt);
    	
    	if(prodOptReq2 == null) {
    		throw new BleApiException(INVALID_ERROR, "productcd 필수 값입니다.");
    	}else {
	    	
    		for(ProdOptReq prodOptReqMap : prodOptReq2) {
			
				//주문옵션조회
		    	List<ProdOpt> prodOptList1 = new ArrayList<ProdOpt>();
		    	List<ProdOpt> prodOptList2 = new ArrayList<ProdOpt>();
		    	List<ProdOpt> prodOptList3 = new ArrayList<ProdOpt>();
		    	
		    	prodOpt.setOptiongroupno(1);
		    	prodOptList1 = dao.getProdOptList(prodOpt);
		    	prodOptReqMap.setOp1_data(prodOptList1);
		    	
		    	prodOpt.setOptiongroupno(2);
		    	prodOptList2 = dao.getProdOptList(prodOpt);
		    	prodOptReqMap.setOp2_data(prodOptList2);
		    	
		    	prodOpt.setOptiongroupno(3);
		    	prodOptList3 = dao.getProdOptList(prodOpt);
		    	prodOptReqMap.setOp3_data(prodOptList3);
			    	
			    rtnProdOptReqList.add(prodOptReqMap);
    		}
    	} 
		return rtnProdOptReqList;
	}
	
	//상품 부분 수정
	@Transactional
	public List<ProdOptReq> setProdOptPartUpdate(ProdOptReq prodOptReq,ProdOpt prodOpt) throws Exception {
		int result = 0;
		
		//주문상세 처리 - 상세에 상품코드가 있어서 상세먼저처리해야됨
		List<ProdOpt> op1_data = prodOptReq.getOp1_data();
		List<ProdOpt> op2_data = prodOptReq.getOp2_data();
		List<ProdOpt> op3_data = prodOptReq.getOp3_data();
		
		if(op1_data == null) {
		}else {
			for (ProdOpt data1 : op1_data) {
				
				data1.setProductcd(prodOptReq.getProductcd());
				if(data1.getIsava().equals(""))
				{
				}else {
					data1.setOptiongroupno(1);
					result = dao.setProdOptPartUpdate(data1);	
				}
			}
		}
		
		if(op2_data == null) {
		}else {
			for (ProdOpt data2 : op2_data) {
				
				data2.setProductcd(prodOptReq.getProductcd());
				if(data2.getIsava().equals(""))
				{
				}else {
					data2.setOptiongroupno(2);
					result = dao.setProdOptPartUpdate(data2);
				}
			}
		}
		
		if(op3_data == null) {
		}else {
			for (ProdOpt data3 : op3_data) {
				
				data3.setProductcd(prodOptReq.getProductcd());
				if(data3.getIsava().equals(""))
				{
				}else {
					data3.setOptiongroupno(3);
					result = dao.setProdOptPartUpdate(data3);
				}
			}
		}
		
		//리턴 정보
		List<ProdOptReq> rtnProdOptReqList = new ArrayList<ProdOptReq>();
		prodOpt.setProductcd(prodOptReq.getProductcd());
		
		//옵션정보조회
    	List<ProdOptReq> prodOptReq2 = new ArrayList<ProdOptReq>();
    	prodOptReq2 = dao.getProdOptCodeList(prodOpt);
    	
    	if(prodOptReq2 == null) {
    		throw new BleApiException(INVALID_ERROR, "productcd 필수 값입니다.");
    	}else {
	    	
    		for(ProdOptReq prodOptReqMap : prodOptReq2) {
			
				//주문옵션조회
		    	List<ProdOpt> prodOptList1 = new ArrayList<ProdOpt>();
		    	List<ProdOpt> prodOptList2 = new ArrayList<ProdOpt>();
		    	List<ProdOpt> prodOptList3 = new ArrayList<ProdOpt>();
		    	
		    	prodOpt.setOptiongroupno(1);
		    	prodOptList1 = dao.getProdOptList(prodOpt);
		    	prodOptReqMap.setOp1_data(prodOptList1);
		    	
		    	prodOpt.setOptiongroupno(2);
		    	prodOptList2 = dao.getProdOptList(prodOpt);
		    	prodOptReqMap.setOp2_data(prodOptList2);
		    	
		    	prodOpt.setOptiongroupno(3);
		    	prodOptList3 = dao.getProdOptList(prodOpt);
		    	prodOptReqMap.setOp3_data(prodOptList3);
			    	
			    rtnProdOptReqList.add(prodOptReqMap);
    		}
    	} 
		return rtnProdOptReqList;
	}
	
	//배송지 등록 
	@Transactional
	public void setShippingInsert(ShippingInfo shippingInfo) {
		int result = 0;
		String shippolicyNo = "";
		
		try {
			result = dao.setShippingInsert(shippingInfo);
			
			if(result > 0) {
				shippolicyNo = dao.getMaxShippolicyNo(shippingInfo);
				shippingInfo.setShippolicyNo(shippolicyNo);
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
			//throw new BleApiException(Code.DUPLICATE, Code.DUPLICATE_MSG);
		}
	}	
	
	//배송지 수정
	@Transactional
	public void setShippingUpdate(ShippingInfo shippingInfo) throws Exception {
		int result = 0;
		
		try {
			result = dao.setShippingUpdate(shippingInfo);	
			
			if(1 > result) {
	    		throw new BleApiException(INVALID_ERROR, "정상적으로 수정되지 않았습니다");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new BleApiException(INVALID_ERROR, "정상적으로 수정되지 않았습니다");
		}
	}
	
	//배송지조회
	@Transactional
	public Map<String, Object> getShipping(ShippingInfo shippingInfo) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();

		try {
			
			data = dao.getShipping(shippingInfo);
			
			if( StringUtils.isEmpty(data)) {
				result.put("code"   , Code.SELECT_IS_NULL);
				result.put("message", Code.SELECT_IS_NULL_MSG);
			} else {
				result.putAll(data);
			}
			
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			//throw new BleApiException(Code.INVALID_ERROR, "중복된 항목이 이미 존재합니다.");
		}
		
		return result;
	}	
	
	//카테고리조회
	@Transactional
	public List<CategoriesInfo> getCategories(CategoriesInfo categoriesInfo) {
		return dao.getCategories(categoriesInfo);	
	}		
	
	//주문조회단건
	public Map<String, Object> getOrder(ProdReq prodReq) throws BleApiException {
		return rtnOrderList(prodReq);
	}

	//주문조회리스트
	public Map<String, Object> getOrderList(ProdReq prodReq) throws BleApiException {
		return rtnOrderList(prodReq);
	}	
	
//	public Map<String,Object> chkStatusInfo(ProdReq prodReq) {
//		
//		HashMap<String, Object> resultMap = new HashMap<String, Object>();
//		
//		//2-15 상태값을 제외한값은 예외처리
//		String status = prodReq.getStatus();
//
//		if( Integer.parseInt(status) < 2 || Integer.parseInt(status) > 15) {
//			LOGGER.error(">>> status : {}", status);
//			resultMap.put("resultCode", ResponseCode.STATUS_ERROR.getRetCode());
//			resultMap.put("resultMsg", ResponseCode.STATUS_ERROR.getRetMsg());
//		}
//		
//		return resultMap;
//	}
	
	//주문조회 - 상세조회 - 옵션조회
	@Transactional
	public Map<String, Object> rtnOrderList(ProdReq prodReq) throws BleApiException {
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<OrderInfo> rtnOrderList = new ArrayList<OrderInfo>();
		
		//주문조회
    	List<OrderInfo> orderList = new ArrayList<OrderInfo>(); 
		orderList = dao.getOrderList(prodReq);
		
		if(0 < orderList.size()) {			
			//주문상세조회 
	        for(OrderInfo orderInfoMap : orderList) {
	        	List<OrderDetailInfo> orderDetailInfoList = new ArrayList<OrderDetailInfo>();
	    		
	        	prodReq.setOrdercd(orderInfoMap.getOrdercd());
	        	orderDetailInfoList = dao.getOrderDetail(prodReq);
	        	
	        	//상세조회에 값이 있을때조회
	        	if(0 < orderDetailInfoList.size()) {
		        	orderInfoMap.setOrderDetail(orderDetailInfoList);
		        	
		    		//옵션조회
		        	for(OrderDetailInfo orderDetailInfoMap : orderDetailInfoList) {
		            	List<OptionInfo> optionInfoList = new ArrayList<OptionInfo>(); 
		            	optionInfoList = dao.getOrderDeOpts(orderDetailInfoMap);
		            	orderDetailInfoMap.setOrderDetailOption(optionInfoList);
		        	}
	        	} else {
	        		orderInfoMap = null; //상세조회에 값이 없으면 주문데이터도 초기화한다
	        	}
	        	rtnOrderList.add(orderInfoMap);
	        }
		}
		
		result.put("order", rtnOrderList);
		
		return result;        
	}
	
	//주문상세수정 주문
	@Transactional
	public HashMap<String, Object> setOrderDeUpdate(OrderDetailInfo orderDetailInfo, String flag) throws BleApiException {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		//2-15 상태값을 제외한값은 예외처리
		String status = orderDetailInfo.getStatus();

		if( Integer.parseInt(status) < 2 || Integer.parseInt(status) > 15) {
			LOGGER.error(">>> status : {}", status);
			//resultMap.put("resultCode", ResponseCode.STATUS_ERROR.getRetCode());
			//resultMap.put("resultMsg", ResponseCode.STATUS_ERROR.getRetMsg());
			//return resultMap;
			
			throw new BleApiException(Code.STATUS_VAL, Code.STATUS_VAL_MSG);
		}
		
		ProdReq prodReq = new ProdReq();

		prodReq.setOrdercd(orderDetailInfo.getOrdercd());     //주문코드
		prodReq.setProductcd(orderDetailInfo.getProductcd()); //상품 코드
		
		//주문상세조회
    	List<OrderDetailInfo> orderDetailInfoList = new ArrayList<OrderDetailInfo>();
    	
    	ProdReq orderInfoReqMap = new ProdReq();
    	orderInfoReqMap.setOrdercd(orderDetailInfo.getOrdercd());
    	
    	orderDetailInfoList = dao.getOrderDetail(orderInfoReqMap);
    	String rtnStatus = orderDetailInfoList.get(0).getStatus();
    	
    	//주문확인처리 - 01:입금대기 02:결제완료
		if(flag.equals("order") && ("01".equals(rtnStatus) || "02".equals(rtnStatus)) ) {
			
			if(!"03".equals(status)) {
				throw new BleApiException(Code.STATUS_VAL, Code.STATUS_VAL_MSG);
			}
			
			prodReq.setStatus(status); //03:주문확인
			
		//배송중처리 - 02:결제완료 03:주문확인
		} else if(flag.equals("ship") && !StringUtil.isEmptyString(orderDetailInfo.getShippingno()) && ("02".equals(rtnStatus) || "03".equals(rtnStatus)) ) {
			
			if(!"04".equals(status)) {
				throw new BleApiException(Code.STATUS_VAL, Code.STATUS_VAL_MSG);
			}			
			
			prodReq.setStatus(status); //04:배송중
			
			prodReq.setShippingno(orderDetailInfo.getShippingno()); //운송장 번호
			prodReq.setDelicomcd(orderDetailInfo.getDelicomcd());   //택배사 코드
			
		//교환확인중처리 - 08:교환요청     
		} else if(flag.equals("exchCon") && "08".equals(rtnStatus)  ) {
			
			if(!"09".equals(status)) {
				throw new BleApiException(Code.STATUS_VAL, Code.STATUS_VAL_MSG);
			}			
			
			prodReq.setStatus("09"); //교환확인중
			
		//반품요청중처리 
		} else if(flag.equals("refuCon") && "13".equals(rtnStatus)  ) {
			
			if(!"14".equals(status)) {
				throw new BleApiException(Code.STATUS_VAL, Code.STATUS_VAL_MSG);
			}				
			prodReq.setStatus("14"); //반품확인중
			
		//취소요청처리 
		} else if(flag.equals("canReq") && ("02".equals(rtnStatus) || "03".equals(rtnStatus)) ) {
			
			if(!"06".equals(status)) {
				throw new BleApiException(Code.STATUS_VAL, Code.STATUS_VAL_MSG);
			}			
			
			prodReq.setStatus("06"); //취소요청
			prodReq.setCancelReason(orderDetailInfo.getCancelReason()); //취소요청
		
		//배송완료중 교환확인중 들어올경우
		} else if("05".equals(rtnStatus) && "09".equals(status) ) {
			//resultMap.put("resultCode", ResponseCode.EXCHANGE_CONFIRM.getRetCode());
			//resultMap.put("resultMsg", ResponseCode.EXCHANGE_CONFIRM.getRetMsg());			
			//return resultMap;
			throw new BleApiException(Code.EXCHANGE_CONFIRM_VAL, Code.EXCHANGE_CONFIRM_VAL_MSG);
			
		//배송완료중 반품확인중 들어올경우			
		} else if("05".equals(rtnStatus) && "14".equals(status) ) {
			throw new BleApiException(Code.REFUUND_CONFIRM_VAL, Code.REFUUND_CONFIRM_VAL_MSG);
			
		} else {
			throw new BleApiException(Code.STATUS_VAL, Code.STATUS_VAL_MSG);
		}
    	
		try {
			dao.setOrderDeUpdate(prodReq);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("header", new ResponseTemplate(ResponseCode.FAIL));
		}
		
		return resultMap;		
		
	}
	
	//상품/주문 문의 등록
	@Transactional
	public void setQnaProductInsert(QnaProdInfo qnaProdInfo) throws Exception {
		int result = 0;		
		try {
			result = dao.setQnaProductInsert(qnaProdInfo);	
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			throw new BleApiException(Code.DUPLICATE, Code.DUPLICATE_MSG);
		} 
	}
   
	//상품/주문 문의 수정
	@Transactional(rollbackFor = Exception.class)
	public void setQnaProdUpdate(QnaProdInfo qnaProdInfo) throws Exception {
		int result = 0;		
		try { 			
			result = dao.setQnaProductUpdate(qnaProdInfo);						
			    
		} catch (Exception e) {  
			e.printStackTrace();			   
		}

		if (result == 0) {
			throw new BleApiException(Code.SELECT_IS_NULL, Code.SELECT_IS_NULL_MSG);
		}  
				
	}

	//상품/주문 문의 목록 조회
	@Transactional
	public List<QnaProdInfo> getQnaProductList(QnaProdInfo qnaProdInfo) {
		return dao.getQnaProductList(qnaProdInfo);	 
	}	

	//상품/주문 답변 리스트 
	public List<QnaAnswerInfo> getQnaAnswerList(QnaAnswerInfo qnaAnswerInfo) {
		List<QnaAnswerInfo> rtnQnaAnswerList = new ArrayList<QnaAnswerInfo>();

		rtnQnaAnswerList = dao.getQnaAnswerList(qnaAnswerInfo);

		return rtnQnaAnswerList;
	}	
	
	//상품/주문 답변 등록 
	@Transactional
	public void setQnaAnswerInsert(QnaAnswerInfo qnaAnswerInfo) throws Exception {
		int result = 0;
		long seq = 0;

		try {
			
			result = dao.setQnaAnswerInsert(qnaAnswerInfo);
			
			if(result > 0) {
				try {
					seq = dao.getQnaAnswerSelect(qnaAnswerInfo);
					qnaAnswerInfo.setSeq(seq);
					
				} catch (DuplicateKeyException e) {
					throw new BleApiException(Code.DUPLICATE, Code.DUPLICATE_MSG);
				}
			}
			
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			throw new BleApiException(Code.DUPLICATE, Code.DUPLICATE_MSG);
		}
	}
	
	//상품/주문 답변 수정
	@Transactional
	public void setQnaAnswerUpdate(QnaAnswerInfo qnaAnswerInfo) throws Exception {
		int result = 0;
		try {
			result = dao.setQnaAnswerUpdate(qnaAnswerInfo);
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			throw new BleApiException(Code.DUPLICATE, Code.DUPLICATE_MSG);
		}
	}
	

}
