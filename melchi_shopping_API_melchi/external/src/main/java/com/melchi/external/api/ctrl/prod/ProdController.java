package com.melchi.external.api.ctrl.prod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.melchi.external.api.service.prod.ProdService;
import com.melchi.external.common.BaseConst.Code;
import com.melchi.external.common.BaseController;
import com.melchi.external.common.ParamValidator;
import com.melchi.external.common.model.BleApiException;
import com.melchi.external.common.model.response.Header;
import com.melchi.external.common.model.response.Response;
import com.melchi.external.module.prod.model.CategoriesInfo;
import com.melchi.external.module.prod.model.OrderDetailInfo;
import com.melchi.external.module.prod.model.ProdInfo;
import com.melchi.external.module.prod.model.ProdNoti;
import com.melchi.external.module.prod.model.ProdNotiReq;
import com.melchi.external.module.prod.model.ProdOpt;
import com.melchi.external.module.prod.model.ProdOptReq;
import com.melchi.external.module.prod.model.ProdReq;
import com.melchi.external.module.prod.model.QnaAnswerInfo;
import com.melchi.external.module.prod.model.QnaProdInfo;
import com.melchi.external.module.prod.model.ShippingInfo;

import lombok.Setter;

@Controller
@RequestMapping(value="/prod")
public class ProdController extends BaseController {

	private final static Logger LOGGER = LoggerFactory.getLogger(ProdController.class);
	
	@Setter
	@Resource ProdService service;

	private Header header;
	
	/**
	 * 상품 등록2
	 * @param prodInfo
	 * @return rtnMsg
	 * @throws Exception
	 */
	@RequestMapping(value="/setProdInfoInsert", method = {RequestMethod.POST})
	@ResponseBody
	public Response setProdInfoInsert(@RequestBody final ProdInfo prodInfo, HttpServletRequest httpReq) throws Exception {
		
		prodInfo.validateSetProdInfoInsert();
		
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			service.setProdInfoInsert(prodInfo);

		} catch (Exception e) {
			return new Response(e.getMessage());
		}
		result.put("productcd", prodInfo.getProductcd());
		result.put("catecd", prodInfo.getCatecd());
		result.put("sellercd", prodInfo.getSellercd());
		
		return new Response(result);
	}
	
	/**
	 * 상품 단건 조회4
	 * @param prodInfo
	 * @return rtnMsg
	 * @throws Exception
	 */
	@RequestMapping(value="/getProdInfo", method = {RequestMethod.POST})
	@ResponseBody
	public Response getProdInfo(@RequestBody final ProdInfo prodInfo, ProdOpt prodOpt, ProdNoti prodNoti, HttpServletRequest httpReq) throws Exception {
		
		prodInfo.validateGetProdInfo();
		
		List<?> result = null;
		
		try {
			result = service.getProdInfo(prodInfo, prodOpt, prodNoti);
			
		} catch (Exception e) {
			return new Response(e.getMessage());
		}
		return new Response(result);
	}
	
	/**
	 * 상품 리스트 조회 
	 * @param OrderInfo
	 * @return result
	 * @throws Exception
	 */
	@RequestMapping(value="/getProdInfoList", method = {RequestMethod.POST})
	@ResponseBody
	public Response getProdInfoList(@RequestBody final ProdInfo prodInfo, ProdOpt prodOpt,ProdNoti prodNoti, HttpServletRequest httpReq) throws Exception {
		
		prodInfo.validateGetProdInfoList();
		
		List<?> result = null;
		
		try {
			result = service.getProdInfoList(prodInfo, prodOpt, prodNoti);

		} catch (Exception e) {
			return new Response(e.getMessage());
		}
		return new Response(result);
	}
	
	/**
	 * 상품 옵션 등록
	 * @param prodOpt
	 * @return rtnMsg
	 * @throws Exception
	 */
	@RequestMapping(value="/setProdOptInsert", method = {RequestMethod.POST})
	@ResponseBody
	public Response setProdOptInsert(@RequestBody final ProdOptReq prodOptReq, ProdOpt prodOpt, HttpServletRequest httpReq) throws Exception {
		
		prodOpt.setProductcd(prodOptReq.getProductcd());
		List<?> result = null;

		try {
			result = service.setProdOptInsert(prodOptReq, prodOpt);

		} catch (Exception e) {
			return new Response(e.getMessage());
		}
		return new Response(result);
	}
	
	/**
	 * 고시 정보 등록
	 * @param prodNoti
	 * @return rtnMsg
	 * @throws Exception
	 */
	@RequestMapping(value="/setProdNotiInsert", method = {RequestMethod.POST})
	@ResponseBody
	public Response setProdNotiInsert(@RequestBody final ProdNotiReq prodNotiReq, ProdNoti prodNoti, HttpServletRequest httpReq) throws Exception {
		
		prodNoti.setProductcd(prodNotiReq.getProductcd());
		List<?> result = null;

		try {
			result = service.setProdNotiInsert(prodNotiReq, prodNoti);

		} catch (Exception e) {
			return new Response(e.getMessage());
		}
		return new Response(result);
	}
	
	/**
	 * 상품 수정 
	 * @param prodInfo
	 * @return rtnMsg
	 * @throws Exception
	 */
	@RequestMapping(value="/setProdInfoUpdate", method = {RequestMethod.POST})
	@ResponseBody
	public Response setProdInfoUpdate(@RequestBody final ProdInfo prodInfo, HttpServletRequest httpReq) throws Exception {
		
		prodInfo.validateSetProdInfoUpdate();
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			service.setProdInfoUpdate(prodInfo);

		} catch (Exception e) {
			return new Response(e.getMessage());
		}
		result.put("sellercd", prodInfo.getSellercd());
		result.put("catecd", prodInfo.getCatecd());
		result.put("productcd", prodInfo.getProductcd());
		return new Response(result);
	}
	
	/**
	 * 상품 부분 수정 
	 * @param prodInfo
	 * @return rtnMsg
	 * @throws Exception
	 */
	@RequestMapping(value="/setProdInfoPartUpdate", method = {RequestMethod.POST})
	@ResponseBody
	public Response setProdInfoPartUpdate(@RequestBody final ProdInfo prodInfo, HttpServletRequest httpReq) throws Exception {
		
		prodInfo.validateSetProdInfoPartUpdate();			
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			service.setProdInfoPartUpdate(prodInfo);

		} catch (Exception e) {
			return new Response(e.getMessage());
		}
		result.put("sellercd", prodInfo.getSellercd());
		result.put("catecd", prodInfo.getCatecd());
		result.put("productcd", prodInfo.getProductcd());
		return new Response(result);
	}
	
	/**
	 * 상품 배송 부분 수정
	 * @param prodInfo
	 * @return rtnMsg
	 * @throws Exception
	 */
	@RequestMapping(value="/setProdInfoPartxUpdate", method = {RequestMethod.POST})
	@ResponseBody
	public Response setProdInfoPartxUpdate(@RequestBody final ProdInfo prodInfo, HttpServletRequest httpReq) throws Exception {
		
		prodInfo.validateSetProdInfoPartxUpdate();			
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			service.setProdInfoPartxUpdate(prodInfo);

		} catch (Exception e) {
			return new Response(e.getMessage());
		}
		result.put("sellercd", prodInfo.getSellercd());
		result.put("catecd", prodInfo.getCatecd());
		result.put("productcd", prodInfo.getProductcd());
		return new Response(result);
	}
	
	/**
	 * 상품 옵션 수정
	 * @param prodOpt
	 * @return rtnMsg
	 * @throws Exception
	 */
	@RequestMapping(value="/setProdOptUpdate", method = {RequestMethod.POST})
	@ResponseBody
	public Response setProdOptUpdate(@RequestBody final ProdOptReq prodOptReq, ProdOpt prodOpt, HttpServletRequest httpReq) throws Exception {
			
		//prodOpt.validateSetProdOptUpdate();
		
		List<?> result = null;
		prodOpt.setProductcd(prodOptReq.getProductcd());
		
		try {
			result = service.setProdOptUpdate(prodOptReq, prodOpt);

		} catch (Exception e) {
			return new Response(e.getMessage());
		}
		return new Response(result);
	}
	
	/**
	 * 고시 정보 수정
	 * @param prodNoti
	 * @return rtnMsg
	 * @throws Exception
	 */
	@RequestMapping(value="/setProdNotiUpdate", method = {RequestMethod.POST})
	@ResponseBody
	public Response setProdNotiUpdate(@RequestBody final ProdNotiReq prodNotiReq, ProdNoti prodNoti, HttpServletRequest httpReq) throws Exception {
			
		List<?> result = null;
		prodNoti.setProductcd(prodNotiReq.getProductcd());
		
		try {
			result = service.setProdNotiUpdate(prodNotiReq, prodNoti);

		} catch (Exception e) {
			return new Response(e.getMessage());
		}
		return new Response(result);
	}
	
	/**
	 * 상품 옵션 부분 수정
	 * @param prodOpt
	 * @return rtnMsg
	 * @throws Exception
	 */
	@RequestMapping(value="/setProdOptPartUpdate", method = {RequestMethod.POST})
	@ResponseBody
	public Response setProdOptPartUpdate(@RequestBody final ProdOptReq prodOptReq, ProdOpt prodOpt, HttpServletRequest httpReq) throws Exception {
		
		//prodOpt.validateSetProdOptPartUpdate();
		
		List<?> result = null;
		prodOpt.setProductcd(prodOptReq.getProductcd());
		
		try {
			result = service.setProdOptPartUpdate(prodOptReq, prodOpt);

		} catch (Exception e) {
			return new Response(e.getMessage());
		}
		return new Response(result);
	}
	
	/**
	 * 배송지등록
	 * @param  ShippingInfo
	 * @return result
	 * @throws Exception
	 */
	@RequestMapping(value="/setShippingInsert", method = {RequestMethod.POST})
	@ResponseBody
    public Response setShippingInsert(HttpServletRequest req, @RequestBody final ShippingInfo shippingInfo) throws Exception  {
		
    	//필수 파라미터 검증
    	ParamValidator pVali = new ParamValidator();
    	String validFlid[] = {"sellercd","postcode1","rnaddress1","rnaddress2","returnpostcode1","returnpostcode1","rnreturnaddress2"};
		pVali.nullChk(shippingInfo, validFlid);
		
		if(!pVali.isAccurate()) {
			//return new Response(new ResponseTemplate(pVali.getErrorCode(), pVali.getErrorMessage()));
			return new Response(new BleApiException(Code.INVALID_ERROR, Code.INVALID_ERROR_MSG));
		}				
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			service.setShippingInsert(shippingInfo);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Response(e.getMessage());
		}
		
		result.put("shippolicyNo", shippingInfo.getShippolicyNo());

		return new Response(result);
	}
	
	/**
	 * 배송지수정
	 * @param  ShippingInfo
	 * @return result
	 * @throws Exception
	 */
	@RequestMapping(value="/setShippingUpdate", method = {RequestMethod.POST})
	@ResponseBody
    public Response setShippingUpdate(HttpServletRequest req, @RequestBody final ShippingInfo shippingInfo) throws Exception {
		
    	//필수 파라미터 검증
    	ParamValidator pVali = new ParamValidator();
    	String validFlid[] = {"sellercd","postcode1","rnaddress1","rnaddress2","returnpostcode1","returnpostcode1","rnreturnaddress2"};
		pVali.nullChk(shippingInfo, validFlid);
		
		if(!pVali.isAccurate()) {
			return new Response(new BleApiException(Code.INVALID_ERROR, Code.INVALID_ERROR_MSG));
		}				
		
		Map<String, Object> result = new HashMap<String, Object>();
		
//		try {
//			service.setShippingUpdate(shippingInfo);

//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return new Response(e.getMessage());
//		}
		
		service.setShippingUpdate(shippingInfo);
		
		result.put("shippingInfo", shippingInfo.getShippolicyNo());

		return new Response(result);
	}
	
	/**
	 * 배송지조회
	 * @param  ShippingInfo
	 * @return result
	 * @throws Exception
	 */
	@RequestMapping(value="/getShipping", method = {RequestMethod.POST})
	@ResponseBody
    public Response getShipping(HttpServletRequest req, @RequestBody final ShippingInfo shippingInfo) {
		
    	//필수 파라미터 검증 
    	ParamValidator pVali = new ParamValidator();
    	String validFlid[] = {"sellercd"};
		pVali.nullChk(shippingInfo, validFlid);
		
		if(!pVali.isAccurate()) {
			return new Response(new BleApiException(Code.INVALID_ERROR, Code.INVALID_ERROR_MSG));
		}				
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			result =  service.getShipping(shippingInfo);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Response(e.getMessage());
		}

		return new Response(result);
	}	
	
	/**
	 * 카테고리조회
	 * @param CategoriesInfo
	 * @return rtnMsg
	 * @throws Exception
	 */
	@RequestMapping(value="/getCategories", method = {RequestMethod.POST})
	@ResponseBody
    public Response getCategories(HttpServletRequest req, @RequestBody final CategoriesInfo categoriesInfo) {

		Map<String, Object> result = new HashMap<String, Object>();
		List<CategoriesInfo> categoriesInfoList = null;
		
		try {
			categoriesInfoList = service.getCategories(categoriesInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		result.put("categories", categoriesInfoList);
		
		return new Response(result);
	}	
	
	
	/**
	 * 주문조회
	 * @param OrderInfo
	 * @return result
	 * @throws BleApiException 
	 * @throws Exception
	 */
	@RequestMapping(value="/getOrder", method = {RequestMethod.POST})
	@ResponseBody
    public Response getOrder(HttpServletRequest req, @RequestBody final ProdReq prodReq) throws BleApiException {
		
    	//필수 파라미터 검증
    	ParamValidator pVali = new ParamValidator();
    	String validFlid[] = {"ordercd"};
		pVali.nullChk(prodReq, validFlid);
		
		if(!pVali.isAccurate()) {
			return new Response(new BleApiException(Code.INVALID_ERROR, Code.INVALID_ERROR_MSG));
		}		

		Map<String, Object> result = null;
		
//		try {
			result = service.getOrder(prodReq);
			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		return new Response(result);
	}
	
	
	/**
	 * 주문조회리스트
	 * @param OrderInfo
	 * @return result
	 * @throws BleApiException 
	 * @throws Exception
	 */
	@RequestMapping(value="/getOrderList", method = {RequestMethod.POST})
	@ResponseBody
    public Response getOrderList(HttpServletRequest req, @RequestBody final ProdReq prodReq) throws BleApiException {
		
    	//필수 파라미터 검증
    	ParamValidator pVali = new ParamValidator();
    	String validFlid[] = {"orderdateStart","orderdateEnd","status"};
		pVali.nullChk(prodReq, validFlid);
		
		if(!pVali.isAccurate()) {
			return new Response(new BleApiException(Code.INVALID_ERROR, Code.INVALID_ERROR_MSG));
		}		

		Map<String, Object> result = null;
		
//		try {
			result = service.getOrderList(prodReq);
			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		return new Response(result);
	}
	
	/**
	 * 주문상세수정 주문
	 * @param  ShippingInfo
	 * @return rtnMsg
	 * @throws BleApiException 
	 * @throws Exception
	 */
	@RequestMapping(value="/setOrderDeOrderUpdate", method = {RequestMethod.POST})
	@ResponseBody
    public Response setOrderDeOrderUpdate(HttpServletRequest req, @RequestBody final OrderDetailInfo orderDetailInfo) throws BleApiException {

		HashMap<String, Object> result = new HashMap<String, Object>();

		//필수 파라미터 검증
		ParamValidator pVali = new ParamValidator();
    	String validFlid[] = {"ordercd","productcd","status"};
		pVali.nullChk(orderDetailInfo, validFlid);
		
		if(!pVali.isAccurate()) {
			return new Response(new BleApiException(Code.INVALID_ERROR, Code.INVALID_ERROR_MSG));
		}
		
//		try {
			result = service.setOrderDeUpdate(orderDetailInfo, "order");
			
//		} catch (Exception e) {
//			e.printStackTrace();
//			result.put("header", e.getMessage());
//		}
		
		result.put("ordercd", orderDetailInfo.getOrdercd());
		result.put("productcd", orderDetailInfo.getProductcd());
		
		return new Response(result); 
	}
	
	/**
	 * 주문상세수정 배송
	 * @param  ShippingInfo
	 * @return rtnMsg
	 * @throws BleApiException 
	 * @throws Exception
	 */
	@RequestMapping(value="/setOrderDeShipUpdate", method = {RequestMethod.POST})
	@ResponseBody
    public Response setOrderDeShipUpdate(HttpServletRequest req, @RequestBody final OrderDetailInfo orderDetailInfo) throws BleApiException {
		
		HashMap<String, Object> result = new HashMap<String, Object>();

		//필수 파라미터 검증
		ParamValidator pVali = new ParamValidator();
    	String validFlid[] = {"ordercd","productcd","shippingno","delicomcd","status"};
		pVali.nullChk(orderDetailInfo, validFlid);
		
		if(!pVali.isAccurate()) {
			return new Response(new BleApiException(Code.INVALID_ERROR, Code.INVALID_ERROR_MSG));
		}
		
//		try {
			result = service.setOrderDeUpdate(orderDetailInfo, "ship");
			
//		} catch (Exception e) {
//			e.printStackTrace();
//			result.put("header", e.getMessage());
//		}
		
		result.put("ordercd", orderDetailInfo.getOrdercd());
		result.put("productcd", orderDetailInfo.getProductcd());
		
		return new Response(result); 
	}	
	
	/**
	 * 주문상세수정 교환확인중
	 * @param  ShippingInfo
	 * @return rtnMsg
	 * @throws BleApiException 
	 * @throws Exception
	 */
	@RequestMapping(value="/setOrderDeExchConUpdate", method = {RequestMethod.POST})
	@ResponseBody
	public Response setOrderDeExchConUpdate(HttpServletRequest req, @RequestBody final OrderDetailInfo orderDetailInfo) throws BleApiException {
		
		HashMap<String, Object> result = new HashMap<String, Object>();

		//필수 파라미터 검증
		ParamValidator pVali = new ParamValidator();
		String validFlid[] = {"ordercd","productcd","status"};
		pVali.nullChk(orderDetailInfo, validFlid);
		
		if(!pVali.isAccurate()) {
			return new Response(new BleApiException(Code.INVALID_ERROR, Code.INVALID_ERROR_MSG));
		}
		
//		try {
			result = service.setOrderDeUpdate(orderDetailInfo, "exchCon");
			
//		} catch (Exception e) {
//			e.printStackTrace();
//			result.put("header", e.getMessage());
//		}
		
		result.put("ordercd", orderDetailInfo.getOrdercd());
		result.put("productcd", orderDetailInfo.getProductcd());
		
		return new Response(result);  
	}	
	
	/**
	 * 주문상세수정 반품확인중
	 * @param  ShippingInfo
	 * @return rtnMsg
	 * @throws BleApiException 
	 * @throws Exception
	 */
	@RequestMapping(value="/setOrderDeRefuConUpdate", method = {RequestMethod.POST})
	@ResponseBody
	public Response setOrderDeRefuConUpdate(HttpServletRequest req, @RequestBody final OrderDetailInfo orderDetailInfo) throws BleApiException {
		
		HashMap<String, Object> result = new HashMap<String, Object>();

		//필수 파라미터 검증
		ParamValidator pVali = new ParamValidator();
		String validFlid[] = {"ordercd","productcd","status"};
		pVali.nullChk(orderDetailInfo, validFlid);
		
		if(!pVali.isAccurate()) {
			return new Response(new BleApiException(Code.INVALID_ERROR, Code.INVALID_ERROR_MSG));
		}
		
//		try {
			result = service.setOrderDeUpdate(orderDetailInfo, "refuCon");
			
//		} catch (Exception e) {
//			e.printStackTrace();
//			result.put("header", e.getMessage());
//		}
		
		result.put("ordercd", orderDetailInfo.getOrdercd());
		result.put("productcd", orderDetailInfo.getProductcd());
		
		return new Response(result); 
	}
	
	/**
	 * 주문상세수정 취소요청
	 * @param  ShippingInfo
	 * @return rtnMsg
	 * @throws BleApiException 
	 * @throws Exception
	 */
	@RequestMapping(value="/setOrderDeCanReqUpdate", method = {RequestMethod.POST})
	@ResponseBody
	public Response setOrderDeCanReqUpdate(HttpServletRequest req, @RequestBody final OrderDetailInfo orderDetailInfo) throws BleApiException {
		
		HashMap<String, Object> result = new HashMap<String, Object>();

		//필수 파라미터 검증
		ParamValidator pVali = new ParamValidator();
		String validFlid[] = {"ordercd","productcd","status"};
		pVali.nullChk(orderDetailInfo, validFlid);
		
		if(!pVali.isAccurate()) {
			return new Response(new BleApiException(Code.INVALID_ERROR, Code.INVALID_ERROR_MSG));
		}
		
//		try {
			result = service.setOrderDeUpdate(orderDetailInfo, "canReq");
			
//		} catch (Exception e) {
//			e.printStackTrace();
//			result.put("header", e.getMessage());
//		}
		
		result.put("ordercd", orderDetailInfo.getOrdercd());
		result.put("productcd", orderDetailInfo.getProductcd());
		
		return new Response(result); 
	}
	
	/**
	 * 상품/주문 문의 목록 조회 
	 * @param OrderInfo
	 * @return result
	 * @throws Exception
	 */
	@RequestMapping(value="/getQnaProductList", method = {RequestMethod.POST})
	@ResponseBody
    public Response getQnaProductList(HttpServletRequest req, @RequestBody final QnaProdInfo qnaProdInfo) {
		
    	//필수 파라미터 검증 
    	ParamValidator pVali = new ParamValidator();
    	String validFlid[] = {"startDate", "endDate"};
		pVali.nullChk(qnaProdInfo, validFlid);
		
		if(!pVali.isAccurate()) {
			return new Response(new BleApiException(Code.INVALID_ERROR, Code.INVALID_ERROR_MSG));
		}		

		List<?> result = null;
		
		try {
			result = service.getQnaProductList(qnaProdInfo); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Response(result);
	}	
	
	/**
	 * 상품/주문 문의 등록
	 * @param  ShippingInfo
	 * @return rtnMsg
	 * @throws Exception
	 */
	/*@RequestMapping(value="/setQnaProductInsert", method = {RequestMethod.POST})
	@ResponseBody
    public Response setQnaProductInsert(HttpServletRequest req, @RequestBody final QnaProdInfo qnaProdInfo) throws Exception {
		
    	//필수 파라미터 검증
    	ParamValidator pVali = new ParamValidator();
    	String validFlid[] = {"subject", "productcd"};
		pVali.nullChk(qnaProdInfo, validFlid); 
		
		if(!pVali.isAccurate()) {
			return new Response(new ResponseTemplate(pVali.getErrorCode(), pVali.getErrorMessage()));
		}				

		Map<String, String> rtnMsg = new HashMap<String, String>();				 
		service.setQnaProductInsert(qnaProdInfo);		 
		rtnMsg.put("qnaProdInfo", qnaProdInfo.getSeq());
		return new Response(rtnMsg);  
	}*/
	
	/**
	 * 상품/주문 문의 수정
	 * @param  ShippingInfo
	 * @return rtnMsg
	 * @throws Exception
	 */
	/*@RequestMapping(value="/setQnaProductUpdate", method = {RequestMethod.POST})
	@ResponseBody
    public Response setQnaProductUpdate(HttpServletRequest req, @RequestBody final QnaProdInfo qnaProdInfo) throws Exception {
		 
    	//필수 파라미터 검증 
    	ParamValidator pVali = new ParamValidator();
    	String validFlid[] = {"seq", "subject", "productcd"};
		pVali.nullChk(qnaProdInfo, validFlid);
		
		if(!pVali.isAccurate()) {
			return new Response(new BleApiException(Code.INVALID_ERROR, Code.INVALID_ERROR_MSG));
		}				
		 
		Map<String, String> rtnMsg = new HashMap<String, String>();				  
		service.setQnaProdUpdate(qnaProdInfo);		
		rtnMsg.put("qnaProdInfo", qnaProdInfo.getSeq());  

		return new Response(rtnMsg);
	}		*/
	
	
	/**
	 * 상품/주문 답변 리스트
	 * @param qnaAnswerInfo
	 * @return result
	 * @throws Exception
	 */
	/*@RequestMapping(value="/getQnaAnswerList", method = {RequestMethod.POST})
	@ResponseBody
    public Response getQnaAnswerList(HttpServletRequest req, @RequestBody final QnaAnswerInfo qanAnswerInfo) {		

		Map<String, Object> result = new HashMap<String, Object>();
		List<QnaAnswerInfo> qnaAnswerList = null;
		
		try {
			qnaAnswerList = service.getQnaAnswerList(qanAnswerInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		result.put("qnaAnswerList", qnaAnswerList);
		
		return new Response(result);
	}*/
	
	/**
	 * 상품/주문 답변 등록 
	 * @param qnaAnswerInfo
	 * @return rtnMsg
	 * @throws Exception
	 */
	@RequestMapping(value="/setQnaAnswerInsert", method = {RequestMethod.POST})
	@ResponseBody
	public Response setQnaAnswerInsert(@RequestBody final QnaAnswerInfo qnaAnswerInfo, HttpServletRequest httpReq) throws Exception {
		
    	//필수 파라미터 검증
    	ParamValidator pVali = new ParamValidator();
    	String validFlid[] = {"subject","parentseq"};
		pVali.nullChk(qnaAnswerInfo, validFlid);
		
		if(!pVali.isAccurate()) {
			return new Response(new BleApiException(Code.INVALID_ERROR, Code.INVALID_ERROR_MSG));
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			service.setQnaAnswerInsert(qnaAnswerInfo);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Response(e.getMessage());
		}
		result.put("seq", qnaAnswerInfo.getSeq());
		
		return new Response(result);
	}
	
	/**
	 * 상품/주문 답변 수정 
	 * @param qnaAnswerInfo
	 * @return rtnMsg
	 * @throws Exception
	 */
	@RequestMapping(value="/setQnaAnswerUpdate", method = {RequestMethod.POST})
	@ResponseBody
	public Response setQnaAnswerUpdate(@RequestBody final QnaAnswerInfo qnaAnswerInfo, HttpServletRequest httpReq) throws Exception {
		
    	//필수 파라미터 검증
    	ParamValidator pVali = new ParamValidator();
    	String validFlid[] = {"subject"};
		pVali.nullChk(qnaAnswerInfo, validFlid);
		
		if(!pVali.isAccurate()) {
			return new Response(new BleApiException(Code.INVALID_ERROR, Code.INVALID_ERROR_MSG));
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			service.setQnaAnswerUpdate(qnaAnswerInfo);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Response(e.getMessage());
		}
		result.put("seq", qnaAnswerInfo.getSeq());
		
		return new Response(result);
	}
	
	
}	
	


