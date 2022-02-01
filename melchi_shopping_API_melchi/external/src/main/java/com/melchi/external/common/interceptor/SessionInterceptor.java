package com.melchi.external.common.interceptor;

import static com.melchi.external.common.BaseConst.Code.AUTH_TOKEN_EXPIRED;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.melchi.external.common.model.BleApiException;

public class SessionInterceptor extends HandlerInterceptorAdapter {
	
	@Value("#{config['melchi.api.key.info']}")
	private String apiKeyInfo;
	
	@Value("#{config['melchi.server.info']}")
	private String serverInfo;
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		//인증키걸려서 임시막음
		/*if(!serverInfo.equals("local")) {
	
			String sellerCd = request.getHeader("sellerCd");
			String apiKey = request.getHeader("apiKey");
			int apiKeyCnt = 0;
			
			//파일 객체 생성
	        File file = new File(apiKeyInfo);
	        //입력 스트림 생성
	        FileReader filereader = new FileReader(file);
	        //입력 버퍼 생성
	        BufferedReader bufReader = new BufferedReader(filereader);
	        String line = "";
	        while((line = bufReader.readLine()) != null){
	
	        	String[] values = line.split(","); 
	        	
	            if(sellerCd.equals(values[0]) && apiKey.equals(values[1]) ) {
	            	apiKeyCnt = apiKeyCnt + 1;
	            }
	        }
	        //.readLine()은 끝에 개행문자를 읽지 않는다.            
	        bufReader.close();
	        
	        if(apiKeyCnt == 0) {
	        	throw new BleApiException(AUTH_TOKEN_EXPIRED, "인증키 번호가 잘못되었습니다.");
			}
		}*/
		return super.preHandle(request, response, handler);
	}
}